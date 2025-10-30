package com.project.moru.service;

import com.project.moru.domain.dto.user.RegisterRequestDto;
import com.project.moru.domain.entity.user.User;
import com.project.moru.mapper.struct.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  
  private final UserConverter userConverter;
  private final PasswordEncoder passwordEncoder;
  
  private final UserDataService userDataService;
  
  public void registerUser(RegisterRequestDto request) {
    // 1. 검증 로직
    
    // 2. 비밀번호 암호화
    String password = request.getPassword();
    request.setPassword(passwordEncoder.encode(password));
    
    System.out.println("before: " + request);
    
    // 3. DTO -> Entity 변환
    User registerUserData = userConverter.toEntity(request);
    
    System.out.println("after: " + registerUserData);
    
    userDataService.saveUser(registerUserData);
  }
}
