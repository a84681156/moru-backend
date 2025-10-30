package com.project.moru.domain.dto.user;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"accessToken", "refreshToken"})
public class LoginResultDto {
  
  private String username;
  private String name;
  private String accessToken;
  private String refreshToken;
  
  public LoginResponseDto toResponseDto() {
    return LoginResponseDto.builder()
        .username(this.username)
        .name(this.name)
        .accessToken(this.accessToken)
        .build();
  }
}
