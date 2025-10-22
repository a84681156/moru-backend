package com.project.moru.mapper.struct;

import com.project.moru.domain.dto.DummyDto;
import com.project.moru.domain.entity.Dummy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DummyConverter {
  Dummy toEntity(DummyDto dummyDto);
  
  DummyDto toDto(Dummy dummy);
  
  List<DummyDto> toDtoList(List<Dummy> dummies);
}
