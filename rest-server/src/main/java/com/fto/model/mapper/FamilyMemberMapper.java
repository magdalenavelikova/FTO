package com.fto.model.mapper;

import com.fto.model.dto.FamilyMemberDto;
import com.fto.model.entity.FamilyMemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FamilyMemberMapper {

    FamilyMemberEntity familyMemberDtoToFamilyMemberEntity(FamilyMemberDto familyMemberDto);
    FamilyMemberDto familyMemberEntityToFamilyMemberDto(FamilyMemberEntity familyMemberEntity);
}
