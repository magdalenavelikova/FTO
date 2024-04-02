package com.fto.service;

import com.fto.model.AppUserDetails;
import com.fto.model.dto.FamilyDto;

import java.util.List;

public interface FamilyService {


    FamilyDto createFamily(FamilyDto family, AppUserDetails user);

    List<FamilyDto> getAll(AppUserDetails user);
}
