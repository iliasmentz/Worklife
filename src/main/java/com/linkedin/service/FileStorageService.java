package com.linkedin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

	// TODO: 20/9/2018 Maybe move this to application.configuration 
	public final static String filePath = "./uploads";

	private final Path fileSavePath;

	@Autowired
	public FileStorageService() {
		this.fileSavePath = Paths.get(filePath)
						.toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileSavePath);
		} catch (Exception ex) {
			throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

	public String storeFile(MultipartFile file, String fileNameToSave) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(fileNameToSave);

		try {
			// Check if the file's name contains invalid characters
			if(fileName.contains("..")) {
				throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileSavePath.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileSavePath.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			} else {
				throw new RuntimeException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new RuntimeException("File not found " + fileName, ex);
		}
	}
}