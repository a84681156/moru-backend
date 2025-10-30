package com.project.moru.domain.dto.auth;

import lombok.*;

import java.util.concurrent.TimeUnit;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RefreshTokenDto {
  
  private String username;
  private String refreshToken;
  private long duration;
  private TimeUnit unit;
}
