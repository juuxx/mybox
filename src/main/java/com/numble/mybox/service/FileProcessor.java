package com.numble.mybox.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.numble.mybox.dto.FileDto;
import com.numble.mybox.entity.FileMeta;

import jakarta.servlet.http.HttpServletResponse;

public interface FileProcessor {
	FileDto uploadFile(MultipartFile file, String folderPath, Long userId) throws IOException;

	void fileDownload(HttpServletResponse response, FileMeta fileMeta) throws IOException;

	void deleteFile(String filePath);
}
