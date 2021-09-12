package com.bridgelabz.fileio.main;

import java.io.IOException;


import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class JavaWatchService {

	private WatchService watcher;
	private HashMap<WatchKey, Path> dirWatchers;

	public JavaWatchService(Path dir) throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
		this.dirWatchers = new HashMap<WatchKey, Path>();
		scanAndRegiterDirectories(dir);
	}

	private void scanAndRegiterDirectories(Path path) throws IOException {
		// TODO Auto-generated method stub
		Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes att) throws IOException {
				registerDirWatchers(dir);
				return FileVisitResult.CONTINUE;
			}
		});

	}

	private void registerDirWatchers(Path dir) throws IOException {
		// TODO Auto-generated method stub
		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		dirWatchers.put(key, dir);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void processEvents() {
		while (true) {
			WatchKey key;
			try {
				key = watcher.take();
			} catch (InterruptedException x) {
				return;
			}
			Path dir = dirWatchers.get(key);
			if (dir == null) {
				continue;
			}
			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				Path name = ((WatchEvent<Path>) event).context();
				Path child = dir.resolve(name);
				System.out.format("%s: %s\n", event.kind().name(), child);

				if (kind == ENTRY_CREATE) {
					try {
						if (Files.isDirectory(child)) {
							scanAndRegiterDirectories(child);
						}
					} catch (IOException x) {

					}
				} else if (kind.equals(ENTRY_DELETE)) {
					if (Files.isDirectory(child))
						dirWatchers.remove(key);
				}
			}
			boolean result = key.reset();
			if (!result) {
				dirWatchers.remove(key);
				if (dirWatchers.isEmpty()) {
					break;
				}
			}
		}
	}

}
