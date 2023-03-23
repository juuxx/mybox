package com.numble.mybox.entity;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder @NoArgsConstructor
@AllArgsConstructor
@Getter
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

	@Column(name = "user_id")
	private Long userId;

	/**
	 * 부모폴더 id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_folder_id")
	private Folder parent;

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

	/**
	 * 폴더 순번
	 */
	@Column(name = "folder_seq")
	private Integer folderSeq;

	/**
	 * 삭제유무 0:정상, 1:삭제
	 */
	@Column(name = "remove")
	@Builder.Default
	private Boolean remove = false;

	/**
	 * 생성자 id
	 */
	@Column(name = "create_user_id")
	private Long createUserId;

	@OneToMany(mappedBy = "parent")
	@OrderBy("folderSeq ASC")
	@Builder.Default
	private List<Folder> subFolders = new ArrayList<>();

}
