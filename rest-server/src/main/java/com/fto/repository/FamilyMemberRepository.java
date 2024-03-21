package com.fto.repository;

import com.fto.model.entity.FamilyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyMemberRepository extends JpaRepository<FamilyMemberEntity,Long> {
}
