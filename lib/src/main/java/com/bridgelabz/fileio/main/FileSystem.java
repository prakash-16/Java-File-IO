package com.bridgelabz.fileio.main;

import java.io.File;

public class FileSystem {

	public static boolean deleteFiles(File FilesToBeDeleted) {
		File[] files = FilesToBeDeleted.listFiles();
		if (files != null) {
			for (File file : files) {
				deleteFiles(file);
			}
		}
		return FilesToBeDeleted.delete();
	}

}
