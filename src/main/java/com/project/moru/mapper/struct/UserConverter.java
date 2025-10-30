package com.project.moru.mapper.struct;

import com.project.moru.domain.dto.user.RegisterRequestDto;
import com.project.moru.domain.entity.user.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface UserConverter {
  User toEntity(RegisterRequestDto registerRequestDto);
}
