package com.numble.mybox.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.numble.mybox.entity.Folder;
import com.numble.mybox.repository.FolderRepository;
import com.numble.mybox.response.FolderResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service @Transactional
public class FolderServiceImpl implements FolderService {

	private final FolderRepository folderRepository;

	@Override
	public List<FolderResponse> getFolderList(Long userId, Long topLevelFolderId) {
		List<Folder> folders = folderRepository.findByUserIdAndParent_Id(userId, topLevelFolderId);
		return folders.stream().map(FolderResponse::of).collect(Collectors.toList());
	}
}
