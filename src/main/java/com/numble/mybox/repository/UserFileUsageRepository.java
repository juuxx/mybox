package com.numble.mybox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.numble.mybox.entity.UserFileUsage;

public interface UserFileUsageRepository extends JpaRepository<UserFileUsage, Long>, JpaSpecificationExecutor<UserFileUsage> {

}