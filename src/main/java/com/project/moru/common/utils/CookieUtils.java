package com.project.moru.common.utils;

import org.springframework.http.ResponseCookie;

public final class CookieUtils {
  
  public static ResponseCookie createRefreshTokenCookie(String token) {
    return ResponseCookie.from("refreshToken", token)
        .httpOnly(true)
        .secure(true)
        .path("/")
        .sameSite("None")
        .maxAge(60 * 60 * 24 * 7)
        .build();
  }
  
  public static ResponseCookie expiredCookie() {
    return ResponseCookie.from("refreshToken", "")
        .httpOnly(true)
        .secure(true)
        .path("/")
        .sameSite("None")
        .maxAge(0)
        .build();
  }
  
  private CookieUtils() {}
}
