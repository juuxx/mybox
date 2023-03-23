package com.numble.mybox.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.numble.mybox.response.FolderResponse;
import com.numble.mybox.service.FolderService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FolderController {

	private final FolderService folderService;

	@GetMapping("/folders")
	public ResponseEntity<List<FolderResponse>> getFolderList(@RequestParam(required = false) Long topLevelFolderId) {
		Long userId = 1L;
		List<FolderResponse> responses = folderService.getFolderList(userId, topLevelFolderId);
		return ResponseEntity.ok(responses);
	}

}
