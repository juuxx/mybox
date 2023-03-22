package com.numble.mybox.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.numble.mybox.request.FileDownloadRequest;
import com.numble.mybox.request.FileUploadRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface FileService {
	void uploadFiles(Long userId, FileUploadRequest request);

	void downloadFile(Long fileId, Long userId, HttpServletResponse response) throws IOException;

	void removeFile(Long userId, Long fileId);
}
