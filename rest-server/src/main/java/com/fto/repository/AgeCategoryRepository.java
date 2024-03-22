package com.fto.repository;

import com.fto.model.entity.AgeCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AgeCategoryRepository extends JpaRepository<AgeCategoryEntity,Long> {
}
