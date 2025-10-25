package com.project.moru.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "dummy")
public class Dummy {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")  // DB 컬럼: user_id (snake_case)
  private Long userId;  // Java 필드: userId (camelCase)
  
  @Column(name = "userName", length = 50)
  private String userName;
  
  @Column(name = "name", length = 50)
  private String name;
  
  @Column(name = "email", length = 50)
  private String email;
  
  @Column(name = "role", length = 50)
  private String role;
}
