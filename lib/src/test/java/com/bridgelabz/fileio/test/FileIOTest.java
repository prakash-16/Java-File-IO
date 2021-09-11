package com.bridgelabz.fileio.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import com.bridgelabz.fileio.main.FileSystem;

public class FileIOTest {

	private static String userHome = System.getProperty("user.home");
	private static String fileName = "temp";

	@Test
	public void checkFileOperations() throws IOException {
		Path homePath = Paths.get(userHome);
		System.out.println(homePath);
		Assert.assertTrue(Files.exists(homePath));

		Path checkPath = Paths.get(userHome + "/" + fileName);
		if (Files.exists(checkPath)) {
			FileSystem.deleteFiles(checkPath.toFile());
		}
		Assert.assertFalse(Files.exists(checkPath));
		
		Files.createDirectory(checkPath);
		Assert.assertTrue(Files.exists(checkPath));
		
		IntStream.range(1, 10).forEach(n -> {
			Path tempFile = Paths.get(checkPath+"/temp"+n);
			Assert.assertTrue(Files.notExists(tempFile));
			try {
				Files.createFile(tempFile);
			}
			catch(IOException e){
				
			}
			Assert.assertTrue(Files.exists(tempFile));
		});
		
		Files.list(checkPath).filter(Files::isRegularFile).forEach(System.out::println);
		Files.newDirectoryStream(checkPath).forEach(System.out::println);
		Files.newDirectoryStream(checkPath, n -> n.toFile().isFile() && n.toString().startsWith("temp")).forEach(System.out::println);
		
	}

}
