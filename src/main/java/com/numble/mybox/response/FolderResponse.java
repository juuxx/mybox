package com.numble.mybox.response;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.numble.mybox.entity.Folder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class FolderResponse {
	private Long id;
	private String folderName;
	private Integer folderDepth;
	private Integer folderSeq;
	private Long parentsFolderId;
	private List<FolderResponse> subFolders;

	public static FolderResponse of(Folder folder) {
		return FolderResponse.builder()
			.id(folder.getId())
			.folderName(folder.getFolderName())
			.folderDepth(folder.getFolderDepth())
			.folderSeq(folder.getFolderSeq())
			.parentsFolderId(folder.getParent() != null ? folder.getParent() .getId() : null)
			.subFolders(getSubFolders(folder.getSubFolders()))
			.build();
	}

	private static List<FolderResponse> getSubFolders(List<Folder> subFolders) {
		return subFolders.size() != 0 ? subFolders.stream().map(FolderResponse::of).collect(Collectors.toList()) : null;
	}
}
