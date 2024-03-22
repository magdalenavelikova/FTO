package com.fto.model.mapper;


import com.fto.model.dto.UserDto;
import com.fto.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity userDtoToUserEntity(UserDto userDto);

    @Mapping(source = "created", target = "created", dateFormat = "dd.MM.yyyy")
    UserDto userEntityToUserDto(UserEntity userEntity);

}
