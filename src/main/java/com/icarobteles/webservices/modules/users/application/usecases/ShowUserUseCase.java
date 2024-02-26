package com.icarobteles.webservices.modules.users.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.icarobteles.webservices.modules.users.domain.entities.User;
import com.icarobteles.webservices.modules.users.domain.exceptions.UserNotFoundException;
import com.icarobteles.webservices.modules.users.domain.repositories.UsersRepository;

import lombok.AllArgsConstructor;

/**
 * Caso de uso para buscar um usuário cadastrado.
 */
@AllArgsConstructor
@Service
public class ShowUserUseCase {
  private final UsersRepository usersRepository;

  public User execute(UUID id) throws UserNotFoundException {
    return this.usersRepository.findById(id).orElseThrow(UserNotFoundException::new);
  }
}
