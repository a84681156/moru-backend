package com.project.moru.service;

import com.project.moru.domain.entity.user.User;
import com.project.moru.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDataService {
  
  private final UserRepository userRepository;
  
  public void saveUser(User user) {
    userRepository.save(user);
  }
}
