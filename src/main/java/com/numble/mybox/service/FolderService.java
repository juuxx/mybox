package com.numble.mybox.service;

import java.util.List;

import com.numble.mybox.response.FolderResponse;

public interface FolderService {
	List<FolderResponse> getFolderList(Long userId, Long topLevelFolderId);
}
