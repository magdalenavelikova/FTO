package com.fto.repository;

import com.fto.model.entity.FamilyMemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FamilyMemberRepository extends JpaRepository<FamilyMemberEntity,Long> {
}
