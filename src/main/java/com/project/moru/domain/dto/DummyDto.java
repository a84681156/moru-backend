package com.project.moru.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "Dummy 데이터 DTO")
public class DummyDto {
  
  @Schema(description = "사용자 ID")
  private Long userId;
  
  @Schema(description = "사용자명")
  private String userName;
  
  @Schema(description = "이름")
  private String name;
  
  @Schema(description = "이메일")
  private String email;
  
  @Schema(description = "역할")
  private String role;
}
