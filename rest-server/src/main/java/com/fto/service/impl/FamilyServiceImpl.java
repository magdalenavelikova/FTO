package com.fto.service.impl;

import com.fto.exception.FamilyMemberNameNotUniqueException;
import com.fto.exception.FamilyNameNotUniqueException;
import com.fto.exception.FamilyNotFoundException;
import com.fto.exception.UserNotFoundException;
import com.fto.model.AppUserDetails;
import com.fto.model.dto.FamilyDto;
import com.fto.model.dto.FamilyEditDto;
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
import java.util.ArrayList;
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

    @Override
    public void deleteFamily(Long id) {
        FamilyEntity family = familyRepository.findById(id).orElseThrow(() -> new FamilyNotFoundException(id));
        familyMemberRepository.deleteAll(family.getMembers());
        familyRepository.deleteById(id);
    }

    @Override
    public FamilyViewDto editFamily(Long id, FamilyEditDto family) {
        FamilyEntity existingFamily = familyRepository.findById(id).orElseThrow(() -> new FamilyNotFoundException(id));
        FamilyEntity editedFamily = familyMapper.familyEditDtoToFamilyEntity(family);
        compareAndUpdate(existingFamily, editedFamily);

        return familyMapper.familyEntityToFamilyViewDto(existingFamily);
    }

    private void compareAndUpdate(FamilyEntity existingFamily, FamilyEntity editedFamily) {
        if (!existingFamily.getName().equals(editedFamily.getName())) {
            validateFamilyName(existingFamily, editedFamily);
            existingFamily.setName(editedFamily.getName());
            existingFamily.setModified(LocalDateTime.now());
            familyRepository.save(existingFamily);
        }
        if (editedFamily.getMembers().isEmpty()) {
            existingFamily.getMembers().forEach(m -> familyMemberRepository.deleteById(m.getId()));
            existingFamily.getMembers().clear();
        } else {
            compareMembers(existingFamily, existingFamily.getMembers(), editedFamily.getMembers());
        }

    }


    private void compareMembers(FamilyEntity existingFamily, List<FamilyMemberEntity> existingMembers, List<FamilyMemberEntity> editedMembers) {
        List<FamilyMemberEntity> newMembersList = new ArrayList<>();
        for (FamilyMemberEntity editedMember : editedMembers) {
            boolean isNew = true;
            boolean isEdited = false;
            for (FamilyMemberEntity existingMember : existingMembers) {
                if (existingMember.getId().equals(editedMember.getId())) {
                    if (!existingMember.getName().equals(editedMember.getName())) {
                        validateMember(editedMember.getName(), existingFamily.getName());
                        existingMember.setName(editedMember.getName());
                        isEdited = true;
                    }
                    if (!existingMember.getPinCode().equals(editedMember.getPinCode())) {
                        existingMember.setPinCode(editedMember.getPinCode());
                        isEdited = true;
                    }
                    if (!existingMember.getAgeCategory().equals(editedMember.getAgeCategory())) {
                        existingMember.setAgeCategory(editedMember.getAgeCategory());
                        isEdited = true;
                    }
                    if (!existingMember.getRole().equals(editedMember.getRole())) {
                        existingMember.setRole(editedMember.getRole());
                        isEdited = true;
                    }
                    if (isEdited) {
                        existingMember.setModified(LocalDateTime.now());
                    }
                    newMembersList.add(existingMember);
                    isNew = false;
                    break;
                }
            }

            if (isNew) {
                validateMember(editedMember.getName(), existingFamily.getName());
                editedMember.setFamily(existingFamily);
                editedMember.setCreated(LocalDateTime.now());
                newMembersList.add(editedMember);
            }


        }
        if (!newMembersList.isEmpty()) {

            for (FamilyMemberEntity existingMember : existingMembers) {
                Long deletedId = null;
                for (FamilyMemberEntity editedMember : newMembersList) {
                    if (!existingMember.getId().equals(editedMember.getId())) {
                        deletedId = existingMember.getId();
                    }else{
                        deletedId = null;
                    }
                }
                if (deletedId != null) {
                    familyMemberRepository.deleteById(deletedId);
                }
            }

            List<FamilyMemberEntity> saved = familyMemberRepository.saveAll(newMembersList);
            existingFamily.setModified(LocalDateTime.now());
            existingFamily.setMembers(saved);
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

    private void validateFamilyName(FamilyEntity existingFamily, FamilyEntity editedFamily) {
        if (familyRepository.findByNameAndUserEmail(editedFamily.getName(), existingFamily.getUser().getEmail()).isPresent()) {
            throw new FamilyNameNotUniqueException(editedFamily.getName());
        }
    }

}
