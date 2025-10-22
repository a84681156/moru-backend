package com.project.moru.controller;

import com.project.moru.service.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dummy")
@RequiredArgsConstructor
public class DummyController {
  
  private final DummyService dummyService;
  
  @GetMapping()
  public Map<String, Object> selectAll() {
    
    Map<String, Object> result = new HashMap<>();
    result.put("mybatis", dummyService.selectAllByMybatis());
    result.put("jpa", dummyService.selectAllByJpa());
    
    return result;
  }
}
