package com.fto.service.impl;

import com.fto.model.entity.FamilyRoleEntity;
import com.fto.model.enums.FamilyRoleEnum;
import com.fto.repository.FamilyRoleRepository;
import com.fto.service.FamilyRoleService;
import org.springframework.stereotype.Service;

@Service
public class FamilyRoleServiceImpl implements FamilyRoleService {
private final FamilyRoleRepository familyRoleRepository;

    public FamilyRoleServiceImpl(FamilyRoleRepository familyRoleRepository) {
        this.familyRoleRepository = familyRoleRepository;
    }

    @Override
    public void init() {
        if(familyRoleRepository.count()==0){
            FamilyRoleEntity partner=new FamilyRoleEntity();
            partner.setFamilyRole(FamilyRoleEnum.PARTNER);
            familyRoleRepository.save(partner);

            FamilyRoleEntity child=new FamilyRoleEntity();
            child.setFamilyRole(FamilyRoleEnum.CHILD);
            familyRoleRepository.save(child);

            FamilyRoleEntity parent=new FamilyRoleEntity();
            parent.setFamilyRole(FamilyRoleEnum.PARENT);
            familyRoleRepository.save(parent);

            FamilyRoleEntity sibling=new FamilyRoleEntity();
            sibling.setFamilyRole(FamilyRoleEnum.SIBLING);
            familyRoleRepository.save(sibling);

            FamilyRoleEntity grandchild=new FamilyRoleEntity();
            grandchild.setFamilyRole(FamilyRoleEnum.GRANDCHILD);
            familyRoleRepository.save(grandchild);

            FamilyRoleEntity grandparent=new FamilyRoleEntity();
            grandparent.setFamilyRole(FamilyRoleEnum.GRANDPARENT);
            familyRoleRepository.save(grandparent);

            FamilyRoleEntity sonInLaw=new FamilyRoleEntity();
            sonInLaw.setFamilyRole(FamilyRoleEnum.SON_IN_LAW);
            familyRoleRepository.save(sonInLaw);

            FamilyRoleEntity daughterInLaw=new FamilyRoleEntity();
            daughterInLaw.setFamilyRole(FamilyRoleEnum.DAUGHTER_IN_LAW);
            familyRoleRepository.save(daughterInLaw);

            FamilyRoleEntity unrelated=new FamilyRoleEntity();
            unrelated.setFamilyRole(FamilyRoleEnum.UNRELATED);
            familyRoleRepository.save(unrelated);
        }

    }
}
