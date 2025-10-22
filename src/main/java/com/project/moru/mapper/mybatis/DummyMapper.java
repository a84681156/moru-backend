package com.project.moru.mapper.mybatis;

import com.project.moru.domain.dto.DummyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DummyMapper {
  List<DummyDto> selectAll();
}
