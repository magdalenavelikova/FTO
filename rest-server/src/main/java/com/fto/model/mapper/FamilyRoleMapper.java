package com.fto.model.mapper;


import com.fto.model.entity.FamilyRoleEntity;
import com.fto.model.dto.FamilyRoleDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface FamilyRoleMapper {
    FamilyRoleDto toFamilyRoleDto(FamilyRoleEntity familyRole);

    FamilyRoleEntity toFamilyRoleEntity(FamilyRoleDto familyRoleDto);

}
