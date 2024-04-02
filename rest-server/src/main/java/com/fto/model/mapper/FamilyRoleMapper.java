package com.fto.model.mapper;

import com.fto.model.dto.FamilyRoleDto;
import com.fto.model.entity.FamilyRoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FamilyRoleMapper {

    FamilyRoleEntity familyRoleDtoToFamilyRoleEntity(FamilyRoleDto familyRoleDto);
    FamilyRoleDto familyRoleEntityToFamilyRoleDto(FamilyRoleEntity familyRoleEntity);
}
