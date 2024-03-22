package com.fto.repository;

import com.fto.model.entity.FamilyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMemberEntity,Long> {
}
