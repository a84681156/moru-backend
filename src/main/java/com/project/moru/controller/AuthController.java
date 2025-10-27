package com.project.moru.controller;

import com.project.moru.common.jwt.JwtTokenProvider;
import com.project.moru.domain.dto.LoginRequestDto;
import com.project.moru.domain.dto.LoginResponseDto;
import com.project.moru.domain.dto.RegisterRequestDto;
import com.project.moru.domain.entity.CustomUserDetails;
import com.project.moru.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final AuthService authService;
  
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  
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
    
    authService.registerUser(request);
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
    
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );
    
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    String accessToken = jwtTokenProvider.generateAccessToken(userDetails.getUsername());
    String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails.getUsername());
    
    saveRefreshToken(
        userDetails.getUsername(),
        refreshToken,
        7, TimeUnit.DAYS
    );
    
    ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
        .httpOnly(true)
        .secure(true)
        .path("/")
        .sameSite("None")
        .maxAge(60 * 60 * 24 * 7)
        .build();
    
    httpResponse.setHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    
    LoginResponseDto responseDto = new LoginResponseDto(accessToken, userDetails.getUsername(), userDetails.getName());
    
    return ResponseEntity.ok(responseDto);
  }
  
  private void saveRefreshToken(String username, String refreshToken, long duration, TimeUnit unit) {
    
    // Redis 저장 로직 구현
    
  }
}
