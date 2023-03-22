package com.numble.mybox.dto;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder @AllArgsConstructor @NoArgsConstructor @Getter

public class FileDto {

	private String id;
	private Long userId;
	private String fileOriginName;
	private String fileSaveName;
	private long fileSize;
	private String fileExt;
	private String filePath;
	@Builder.Default
	private LocalDateTime createdAt = LocalDateTime.now();

}
