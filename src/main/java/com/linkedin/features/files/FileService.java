package com.linkedin.features.files;

import com.linkedin.model.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.Random;

@Service
public class FileService {
  public static final String PATH_OF_LINK_DOWNLOAD_WITH_SLASHES = "/downloadFile/";

  private final FileStorageService fileStorageService;
  private final Random randomGenerator;

  @Autowired
  public FileService(FileStorageService fileStorageService) {
    this.fileStorageService = fileStorageService;
    randomGenerator = new Random();
  }


  public UploadFileResponse uploadFile(MultipartFile file, String prefix, boolean random) {
    String generatedFileName = generateFileName(file.getName(), prefix, random);
    String savedFileName = fileStorageService.storeFile(file, generatedFileName);
    String fileDownloadUri = getFileFullUrl(savedFileName);
    return new UploadFileResponse(savedFileName, fileDownloadUri,
        file.getContentType(), file.getSize());
  }

  public static String getFileFullUrl(String fileName) {
    if (fileName != null) {
      return ServletUriComponentsBuilder.fromCurrentContextPath()
          .path(PATH_OF_LINK_DOWNLOAD_WITH_SLASHES)
          .path(fileName)
          .toUriString();
    }
    return null;
  }

  private String generateFileName(String name, String prefix, boolean random) {
    String finalName = "";

    if (prefix != null) {
      finalName += prefix + "-";
    }

    finalName += name + "-";
    finalName += String.valueOf(new Date().getTime());

    if (random) {
      int randomInt = randomGenerator.nextInt();
      finalName += "-" + String.valueOf(randomInt);
    }
    return finalName;
  }
}
