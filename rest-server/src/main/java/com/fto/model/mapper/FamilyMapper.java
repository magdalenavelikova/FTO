package com.fto.model.mapper;

import com.fto.model.dto.FamilyDto;
import com.fto.model.dto.FamilyViewDto;
import com.fto.model.entity.FamilyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FamilyMapper {

    FamilyDto familyEntityToFamilyDto(FamilyEntity familyEntity);
    FamilyViewDto familyEntityToFamilyViewDto(FamilyEntity familyEntity);
    FamilyEntity familyDtoToFamilyEntity( FamilyDto familyDto);

}
