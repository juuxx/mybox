package com.numble.mybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.numble.mybox.entity.FileMeta;

public interface FileMetaRepository extends JpaRepository<FileMeta, Long> {

}