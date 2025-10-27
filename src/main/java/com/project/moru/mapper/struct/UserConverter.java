package com.project.moru.mapper.struct;

import com.project.moru.domain.dto.RegisterRequestDto;
import com.project.moru.domain.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface UserConverter {
  User toEntity(RegisterRequestDto registerRequestDto);
}
