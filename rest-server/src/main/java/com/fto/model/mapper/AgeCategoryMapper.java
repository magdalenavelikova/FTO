package com.fto.model.mapper;

import com.fto.model.dto.AgeCategoryDto;
import com.fto.model.entity.AgeCategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgeCategoryMapper {
    AgeCategoryEntity ageCategoryDtoToAgeCategoryEntity(AgeCategoryDto ageCategoryDto);
    AgeCategoryDto ageCategoryEntityToAgeCategoryDto(AgeCategoryEntity ageCategoryEntity);
}
