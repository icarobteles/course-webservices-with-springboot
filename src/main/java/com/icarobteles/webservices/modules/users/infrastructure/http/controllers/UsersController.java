package com.icarobteles.webservices.modules.users.infrastructure.http.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icarobteles.webservices.modules.users.application.dtos.CreateUserDTO;
import com.icarobteles.webservices.modules.users.application.dtos.UpdateUserDTO;
import com.icarobteles.webservices.modules.users.application.usecases.CreateUserUseCase;
import com.icarobteles.webservices.modules.users.application.usecases.DeleteUserUseCase;
import com.icarobteles.webservices.modules.users.application.usecases.ListUsersUseCase;
import com.icarobteles.webservices.modules.users.application.usecases.ShowUserUseCase;
import com.icarobteles.webservices.modules.users.application.usecases.UpdateUserUseCase;
import com.icarobteles.webservices.modules.users.domain.entities.User;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/users")
public class UsersController {
  private final CreateUserUseCase createUserUseCase;
  private final ListUsersUseCase listUsersUseCase;
  private final ShowUserUseCase showUsersUseCase;
  private final UpdateUserUseCase updateUserUseCase;
  private final DeleteUserUseCase deleteUserUseCase;

  @PostMapping()
  public ResponseEntity<User> create(@RequestBody @Valid CreateUserDTO body) {
    User user = this.createUserUseCase.execute(body);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<User> update(@PathVariable("id") UUID id, @RequestBody @Valid UpdateUserDTO body) {
    User user = this.updateUserUseCase.execute(id, body);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @GetMapping()
  public ResponseEntity<List<User>> list() {
    List<User> users = this.listUsersUseCase.execute();
    return ResponseEntity.status(HttpStatus.OK).body(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> show(@PathVariable("id") UUID id) {
    User user = this.showUsersUseCase.execute(id);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
    this.deleteUserUseCase.execute(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
