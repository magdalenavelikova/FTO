package com.fto.model.mapper;

import com.fto.model.dto.FamilyMemberDto;
import com.fto.model.dto.FamilyMemberEditDto;
import com.fto.model.dto.FamilyMemberViewDto;
import com.fto.model.entity.FamilyMemberEntity;
import com.fto.model.enums.FamilyRoleEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FamilyMemberMapper {

    FamilyMemberEntity familyMemberDtoToFamilyMemberEntity(FamilyMemberDto familyMemberDto);
    FamilyMemberEntity familyMemberEditDtoToFamilyMemberEntity(FamilyMemberEditDto familyMemberEditDto);
    FamilyMemberDto familyMemberEntityToFamilyMemberDto(FamilyMemberEntity familyMemberEntity);
    FamilyMemberViewDto familyMemberEntityToFamilyMemberViewDto(FamilyMemberEntity familyMemberEntity);
}
