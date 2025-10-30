package com.project.moru.controller;

import com.project.moru.common.utils.CookieUtils;
import com.project.moru.domain.dto.user.LoginRequestDto;
import com.project.moru.domain.dto.user.LoginResponseDto;
import com.project.moru.domain.dto.user.LoginResultDto;
import com.project.moru.domain.dto.user.RegisterRequestDto;
import com.project.moru.service.AuthService;
import com.project.moru.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final AuthService authService;
  private final UserService userService;
  
  @Operation(summary = "회원 등록", description = "새로운 회원 정보를 데이터베이스에 저장")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "생성 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @PostMapping("/register")
  public void registerUser
      (
          @RequestBody RegisterRequestDto request
      ) {
    
    userService.registerUser(request);
  }
  
  @Operation(summary = "로그인 API")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "로그인 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(
      @RequestBody LoginRequestDto request,
      HttpServletResponse httpResponse) {
    
    // authService.login 호출
    LoginResultDto loginResultDto = authService.login(request);
    
    // httpOnly Cookie 생성 : refresh token 저장 용도
    ResponseCookie refreshCookie = CookieUtils.createRefreshTokenCookie(loginResultDto.getRefreshToken());
    httpResponse.setHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    
    return ResponseEntity.ok(loginResultDto.toResponseDto());
  }
  
  @Operation(summary = "로그아웃 API")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
      @ApiResponse(responseCode = "400", description = "요청 형식 오류")
  })
  @PostMapping("/logout")
  public  ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse httpResponse) {
    
    // authService.logout 호출
    authService.logout(request);
    
    // refresh token 쿠키 삭제
    ResponseCookie expiredCookie = CookieUtils.expiredCookie();
    httpResponse.setHeader(HttpHeaders.SET_COOKIE, expiredCookie.toString());
    
    return ResponseEntity.ok().build();
  }
  
  // access token 만료 시 재발급 요청 API 구현 필요
  @PostMapping("/refresh")
  public String refresh(HttpServletRequest request) {
    return request.toString();
  }
}
