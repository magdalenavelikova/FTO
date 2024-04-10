package com.fto.service;

import com.fto.model.AppUserDetails;
import com.fto.model.dto.FamilyDto;
import com.fto.model.dto.FamilyMemberDto;
import com.fto.model.dto.FamilyViewDto;

import java.util.List;

public interface FamilyService {


    FamilyViewDto createFamily(FamilyDto family, AppUserDetails user);

    List<FamilyViewDto> getAll(AppUserDetails user);

    FamilyViewDto addMember(FamilyMemberDto member, AppUserDetails user);

    void deleteFamily(Long id);
}
