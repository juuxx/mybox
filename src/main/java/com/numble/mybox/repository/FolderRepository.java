package com.numble.mybox.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.numble.mybox.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {
	List<Folder> findByUserIdAndParent_Id(Long userId, Long topLevelId);

	Optional<Folder> findByParent_IdIsNullAndUserId(Long userId);

	@Query(value = """
			WITH RECURSIVE folder_path AS (
				SELECT id, folder_name, parent_folder_id, folder_seq
				FROM folder
				WHERE id = :folderId
				UNION ALL
				SELECT f.id, f.folder_name, f.parent_folder_id, f.folder_seq
				FROM folder f
				JOIN folder_path fp ON fp.parent_folder_id = f.id
			)
			SELECT GROUP_CONCAT(folder_name ORDER BY folder_seq SEPARATOR '/') as folder_path
			FROM folder_path
		""", nativeQuery = true)
	String getFolderPath(@Param(value = "folderId") Long folderId);
}