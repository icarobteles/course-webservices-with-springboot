package com.icarobteles.webservices.modules.users.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.icarobteles.webservices.modules.users.domain.exceptions.UserNotFoundException;
import com.icarobteles.webservices.modules.users.domain.repositories.UsersRepository;

import lombok.AllArgsConstructor;

/*
 * Caso de uso para deletar um usuário.
 */
@AllArgsConstructor
@Service
public class DeleteUserUseCase {
  private final UsersRepository usersRepository;

  public void execute(UUID id) throws UserNotFoundException {

    this.usersRepository.findById(id).ifPresentOrElse(this.usersRepository::delete, () -> {
      throw new UserNotFoundException();
    });

  }

}
