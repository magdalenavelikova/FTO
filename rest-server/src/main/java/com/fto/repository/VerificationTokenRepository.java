package com.fto.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fto.model.entity.VerificationToken;
import com.fto.model.entity.UserEntity;

import java.util.Optional;

@Transactional
@Repository
public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    Optional<VerificationToken> findByUser(UserEntity user);

    void deleteByUserId(Long id);
}
