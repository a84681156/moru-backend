package com.project.moru.domain.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegisterRequestDto {
  @Schema(description = "회원 아이디", example = "test01")
  private String username;
  @Schema(description = "비밀번호", example = "1234")
  private String password;
  @Schema(description = "회원 이름", example = "테스터1")
  private String name;
}
