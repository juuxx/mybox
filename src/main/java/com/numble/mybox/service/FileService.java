package com.numble.mybox.service;

import com.numble.mybox.request.FileRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface FileService {
	void uploadFiles(Long userId, FileRequest request);

	void downloadFile(Long userId, Long fileId, HttpServletResponse response);

	void removeFile(Long userId, Long fileId);
}
