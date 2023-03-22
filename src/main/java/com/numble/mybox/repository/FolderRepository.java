package com.numble.mybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.numble.mybox.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {

}