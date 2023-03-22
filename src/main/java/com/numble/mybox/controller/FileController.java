package com.numble.mybox.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.numble.mybox.request.FileRequest;
import com.numble.mybox.service.FileService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FileController {

	private final FileService fileService;

	/**
	 * file Upload
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/files/multi/upload")
	public ResponseEntity<HttpStatus> uploadFiles(@ModelAttribute FileRequest request) throws IOException {
		Long userId = 1L;
		this.fileService.uploadFiles(userId, request);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/files/{fileId}")
	public ResponseEntity<HttpStatus> downloadFiles(@PathVariable Long fileId, HttpServletResponse response) throws IOException {
		Long userId = 1L;
		this.fileService.downloadFile(userId, fileId, response);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/files/{fileId}")
	public ResponseEntity<HttpStatus> removeFile(@PathVariable Long fileId) throws IOException {
		Long userId = 1L;
		this.fileService.removeFile(userId, fileId);
		return ResponseEntity.noContent().build();
	}
}
