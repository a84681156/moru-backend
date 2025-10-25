package com.project.moru.controller;

import com.project.moru.service.DummyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Dummy API", description = "테스트용 Dummy 데이터 API")
@RestController
@RequestMapping("/api/dummy")
@RequiredArgsConstructor
public class DummyController {
  
  private final DummyService dummyService;
  
  @Operation(summary = "전체 Dummy 데이터 조회", description = "MyBatis와 JPA를 통해 모든 Dummy 데이터를 조회합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공"),
      @ApiResponse(responseCode = "500", description = "서버 오류")
  })
  @GetMapping()
  public Map<String, Object> selectAll() {
    
    Map<String, Object> result = new HashMap<>();
    result.put("mybatis", dummyService.selectAllByMybatis());
    result.put("jpa", dummyService.selectAllByJpa());
    
    return result;
  }
}
