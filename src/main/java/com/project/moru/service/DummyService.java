package com.project.moru.service;

import com.project.moru.domain.dto.DummyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DummyService {
  
  private final DummyDataService dummyDataService;
  
  public List<DummyDto> selectAllByMybatis() {
    return dummyDataService.selectAllByMybatis();
  }
  
  public List<DummyDto> selectAllByJpa() {
    return dummyDataService.selectAllByJpa();
  }
}
