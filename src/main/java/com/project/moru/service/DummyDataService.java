package com.project.moru.service;

import com.project.moru.domain.dto.DummyDto;
import com.project.moru.mapper.mybatis.DummyMapper;
import com.project.moru.mapper.struct.DummyConverter;
import com.project.moru.repository.DummyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DummyDataService {
  
  private final DummyRepository dummyRepository;
  private final DummyMapper dummyMapper;
  private final DummyConverter dummyConverter;
  
  public List<DummyDto> selectAllByMybatis() {
    return dummyMapper.selectAll();
  }
  
  public List<DummyDto> selectAllByJpa() {
    return dummyConverter.toDtoList(dummyRepository.findAll());
  }
}
