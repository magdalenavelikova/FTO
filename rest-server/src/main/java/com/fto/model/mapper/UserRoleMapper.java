package com.fto.model.mapper;


import com.fto.model.dto.UserRoleDto;
import com.fto.model.entity.UserRoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleDto userRoleEntityToUserRoLeDto(UserRoleEntity userRoleEntity);
}
