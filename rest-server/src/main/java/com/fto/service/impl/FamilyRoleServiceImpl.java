package com.fto.service.impl;

import com.fto.model.dto.FamilyRoleDto;
import com.fto.model.entity.FamilyRoleEntity;
import com.fto.model.enums.FamilyRoleEnum;
import com.fto.model.mapper.FamilyRoleMapper;
import com.fto.repository.FamilyRoleRepository;
import com.fto.service.FamilyRoleService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyRoleServiceImpl implements FamilyRoleService {
    private final FamilyRoleRepository familyRoleRepository;
    private final FamilyRoleMapper familyRoleMapper;

    public FamilyRoleServiceImpl(FamilyRoleRepository familyRoleRepository, FamilyRoleMapper familyRoleMapper) {
        this.familyRoleRepository = familyRoleRepository;
        this.familyRoleMapper = familyRoleMapper;
    }

    //TODO Delete it after test
    @Cacheable("testRedis")
    @Override
    public List<FamilyRoleDto> getAllFamilyRole() {
        return familyRoleRepository.findAll()
                .stream()
                .map(familyRoleMapper::toFamilyRoleDto)
                .toList();
    }



    @Override
    public void init() {
        if (familyRoleRepository.count() == 0) {
            FamilyRoleEntity partner = new FamilyRoleEntity();
            partner.setFamilyRole(FamilyRoleEnum.PARTNER);
            familyRoleRepository.save(partner);

            FamilyRoleEntity child = new FamilyRoleEntity();
            child.setFamilyRole(FamilyRoleEnum.CHILD);
            familyRoleRepository.save(child);

            FamilyRoleEntity parent = new FamilyRoleEntity();
            parent.setFamilyRole(FamilyRoleEnum.PARENT);
            familyRoleRepository.save(parent);

            FamilyRoleEntity sibling = new FamilyRoleEntity();
            sibling.setFamilyRole(FamilyRoleEnum.SIBLING);
            familyRoleRepository.save(sibling);

            FamilyRoleEntity grandchild = new FamilyRoleEntity();
            grandchild.setFamilyRole(FamilyRoleEnum.GRANDCHILD);
            familyRoleRepository.save(grandchild);

            FamilyRoleEntity grandparent = new FamilyRoleEntity();
            grandparent.setFamilyRole(FamilyRoleEnum.GRANDPARENT);
            familyRoleRepository.save(grandparent);

            FamilyRoleEntity sonInLaw = new FamilyRoleEntity();
            sonInLaw.setFamilyRole(FamilyRoleEnum.SON_IN_LAW);
            familyRoleRepository.save(sonInLaw);

            FamilyRoleEntity daughterInLaw = new FamilyRoleEntity();
            daughterInLaw.setFamilyRole(FamilyRoleEnum.DAUGHTER_IN_LAW);
            familyRoleRepository.save(daughterInLaw);

            FamilyRoleEntity unrelated = new FamilyRoleEntity();
            unrelated.setFamilyRole(FamilyRoleEnum.UNRELATED);
            familyRoleRepository.save(unrelated);
        }

    }


}
