package com.numble.mybox.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.numble.mybox.dto.FileDto;

public interface FileUploader {
	FileDto uploadFile(MultipartFile file, String folderPath, Long userId) throws IOException;
}
