package com.numble.mybox.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder @NoArgsConstructor @AllArgsConstructor @Getter
@Entity
@Table(name = "file")
public class FileMeta {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "folder_id")
	private Long folderId;

	@Column(name = "file_origin_name", nullable = false)
	private String fileOriginName;

	@Column(name = "file_save_name", nullable = false)
	private String fileSaveName;

	@Column(name = "file_ext", nullable = false)
	private String fileExt;

	@Column(name = "file_path", nullable = false)
	private String filePath;

	@Column(name = "file_size")
	private long fileSize;

	@Column(name = "created_at")
	@Builder.Default
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "remove")
	@Builder.Default
	private Boolean remove = false;

	public void remove() {
		this.remove = true;
	}
}
