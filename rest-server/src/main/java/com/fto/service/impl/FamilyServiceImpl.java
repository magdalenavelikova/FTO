package com.fto.service.impl;

import com.fto.exception.FamilyMemberNameNotUniqueException;
import com.fto.exception.UserNotFoundException;
import com.fto.model.AppUserDetails;
import com.fto.model.dto.FamilyDto;
import com.fto.model.dto.FamilyMemberDto;
import com.fto.model.dto.FamilyViewDto;
import com.fto.model.entity.FamilyEntity;
import com.fto.model.entity.FamilyMemberEntity;
import com.fto.model.mapper.FamilyMapper;
import com.fto.model.mapper.FamilyMemberMapper;
import com.fto.repository.FamilyMemberRepository;
import com.fto.repository.FamilyRepository;
import com.fto.repository.UserRepository;
import com.fto.service.FamilyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FamilyServiceImpl implements FamilyService {
    private final FamilyRepository familyRepository;
    private final FamilyMemberRepository familyMemberRepository;
    private final UserRepository userRepository;
    private final FamilyMapper familyMapper;
    private final FamilyMemberMapper familyMemberMapper;


    public FamilyServiceImpl(FamilyRepository familyRepository, FamilyMemberRepository familyMemberRepository, UserRepository userRepository, FamilyMapper familyMapper, FamilyMemberMapper familyMemberMapper) {
        this.familyRepository = familyRepository;
        this.familyMemberRepository = familyMemberRepository;
        this.userRepository = userRepository;
        this.familyMapper = familyMapper;
        this.familyMemberMapper = familyMemberMapper;
    }

    @Override
    public FamilyViewDto createFamily(FamilyDto family, AppUserDetails user) {
        FamilyEntity familyEntity = familyMapper.familyDtoToFamilyEntity(family);
        familyEntity.setUser(userRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new UserNotFoundException(user.getUsername())));
        familyEntity.setCreated(LocalDateTime.now());
        return familyMapper.familyEntityToFamilyViewDto(familyRepository.save(familyEntity));
    }

    @Override
    public List<FamilyViewDto> getAll(AppUserDetails user) {
        return familyRepository.findAllByUserEmail(user.getUsername())
                .stream()
                .map(familyMapper::familyEntityToFamilyViewDto)
                .toList();
    }

    @Override
    public FamilyViewDto addMember(FamilyMemberDto familyMemberDto, AppUserDetails user) {
        String familyName = familyMemberDto.getFamilyName();
        Optional<FamilyEntity> family = familyRepository.findByNameAndUserEmail(familyName, user.getUsername());
        validateMember(familyMemberDto.getName(), familyName);
        FamilyMemberEntity member = familyMemberMapper.familyMemberDtoToFamilyMemberEntity(familyMemberDto);
        member.setCreated(LocalDateTime.now());
        if (family.isPresent()) {
            FamilyEntity familyEntity = family.get();
            member.setFamily(familyEntity);
            familyMemberRepository.save(member);
            familyEntity.getMembers().add(member);
            return familyMapper.familyEntityToFamilyViewDto(familyRepository.save(familyEntity));
        } else {
            FamilyEntity newFamily = familyRepository.save(createNewFamily(user, familyName));
            member.setFamily(newFamily);
            familyMemberRepository.save(member);
            newFamily.getMembers().add(member);
            familyRepository.save(newFamily);
            return familyMapper.familyEntityToFamilyViewDto(newFamily);
        }

    }



    private FamilyEntity createNewFamily(AppUserDetails user, String familyName) {
        FamilyEntity newFamily = new FamilyEntity();
        newFamily.setCreated(LocalDateTime.now());
        newFamily.setUser(userRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new UserNotFoundException(user.getUsername())));
        newFamily.setName(familyName);

        return newFamily;
    }
    private void validateMember(String name, String familyName) {
        if (familyMemberRepository.findByNameAndFamily_Name(name, familyName).isPresent()) {
            throw new FamilyMemberNameNotUniqueException(name);
        }
    }
}
