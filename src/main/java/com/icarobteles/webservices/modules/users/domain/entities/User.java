package com.icarobteles.webservices.modules.users.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.icarobteles.webservices.core.CoreEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends CoreEntity {

  @Column(name = "name", nullable = false, length = 75)
  private String name;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @JsonIgnore
  @Column(name = "password", nullable = false)
  private String password;

}
