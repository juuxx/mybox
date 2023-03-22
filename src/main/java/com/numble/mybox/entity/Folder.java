package com.numble.mybox.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "folder")
public class Folder {

	/**
	 * id
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 부모폴더 id
	 */
	@Column(name = "parent_folder_id")
	private Integer parentFolderId;

	/**
	 * 폴더 이름
	 */
	@Column(name = "folder_name")
	private String folderName;

	/**
	 * 폴더 깊이
	 */
	@Column(name = "folder_depth")
	private Integer folderDepth;

	@Column(name = "folder_seq")
	private Integer folderSeq;

	/**
	 * 삭제유무 0:정상, 1:삭제
	 */
	@Column(name = "remove")
	private Integer remove;

	/**
	 * 생성자 id
	 */
	@Column(name = "create_user_id")
	private Long createUserId;

}
