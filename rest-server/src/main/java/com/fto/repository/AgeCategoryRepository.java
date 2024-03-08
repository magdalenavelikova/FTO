package com.fto.repository;

import com.fto.model.entity.AgeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeCategoryRepository extends JpaRepository<AgeCategoryEntity,Long> {
}
