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
  @Column(name = "user_id")
  private Long user_id;
  
  @Column(length = 50)
  private String username;
  
  @Column(length = 50)
  private String name;
  
  @Column(length = 50)
  private String email;
  
  @Column(length = 50)
  private String role;
}
