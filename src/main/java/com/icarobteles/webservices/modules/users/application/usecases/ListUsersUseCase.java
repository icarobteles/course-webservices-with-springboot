package com.icarobteles.webservices.modules.users.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.icarobteles.webservices.modules.users.domain.entities.User;
import com.icarobteles.webservices.modules.users.domain.repositories.UsersRepository;

import lombok.AllArgsConstructor;

/**
 * Caso de uso para listar todos os usuários cadastrados.
 */
@AllArgsConstructor
@Service
public class ListUsersUseCase {

  private final UsersRepository usersRepository;

  public List<User> execute() {
    return this.usersRepository.findAll();
  }

}
