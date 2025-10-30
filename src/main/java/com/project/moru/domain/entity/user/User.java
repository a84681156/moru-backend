package com.project.moru.domain.entity.user;

import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@ToString(exclude = "password")
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")  // DB 컬럼: user_id (snake_case)
  private Long userId;  // Java 필드: userId (camelCase)
  
  @Column(name = "username", nullable = false, length = 50)
  private String username;
  
  @Column(nullable = false)
  private String password;
  
  @Column(name = "name", length = 100)
  private String name;
}
