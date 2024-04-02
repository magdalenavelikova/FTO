package com.fto.service.impl;

import com.fto.exception.UserNotFoundException;
import com.fto.model.AppUserDetails;
import com.fto.model.dto.FamilyDto;
import com.fto.model.entity.FamilyEntity;
import com.fto.model.mapper.FamilyMapper;
import com.fto.repository.FamilyRepository;
import com.fto.repository.UserRepository;
import com.fto.service.FamilyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService {
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final FamilyMapper familyMapper;


    public FamilyServiceImpl(FamilyRepository familyRepository, UserRepository userRepository, FamilyMapper familyMapper) {
        this.familyRepository = familyRepository;
        this.userRepository = userRepository;
        this.familyMapper = familyMapper;
    }

    @Override
    public FamilyDto createFamily(FamilyDto family, AppUserDetails user) {
        FamilyEntity familyEntity = familyMapper.familyDtoToFamilyEntity(family);
        familyEntity.setUser(userRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new UserNotFoundException(user.getUsername())));
        familyEntity.setCreated(LocalDateTime.now());
        return familyMapper.familyEntityToFamilyDto(familyRepository.save(familyEntity));
    }

    @Override
    public List<FamilyDto> getAll(AppUserDetails user) {
        return familyRepository.findAllByUserEmail(user.getUsername())
                .stream()
                .map(familyMapper::familyEntityToFamilyDto)
                .toList();
    }
}
