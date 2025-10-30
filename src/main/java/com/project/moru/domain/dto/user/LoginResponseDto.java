package com.project.moru.domain.dto.user;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "accessToken")
public class LoginResponseDto {
  private String username;
  private String name;
  private String accessToken;
}
