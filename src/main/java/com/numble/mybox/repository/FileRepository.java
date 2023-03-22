package com.numble.mybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.numble.mybox.entity.File;

public interface FileRepository extends JpaRepository<File, String> {

}