package com.icarobteles.webservices.modules.users.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.icarobteles.webservices.modules.users.application.dtos.UpdateUserDTO;
import com.icarobteles.webservices.modules.users.domain.entities.User;
import com.icarobteles.webservices.modules.users.domain.exceptions.EmailAlreadyRegisteredException;
import com.icarobteles.webservices.modules.users.domain.exceptions.UserNotFoundException;
import com.icarobteles.webservices.modules.users.domain.repositories.UsersRepository;

import lombok.AllArgsConstructor;

/**
 * Caso de uso para atualizar um usuário.
 */
@AllArgsConstructor
@Service
public class UpdateUserUseCase {
  private final UsersRepository usersRepository;

  public User execute(UUID id, UpdateUserDTO data) throws UserNotFoundException, EmailAlreadyRegisteredException {
    // Verifica se o usuário existe.
    User user = findUser(id);

    // Atualiza o email, caso tenha sido informado e já não esteja em uso.
    data.email().ifPresent(email -> this.updateEmail(email, user));

    // Atualiza o nome, caso tenha sido informado.
    data.name().ifPresent(name -> this.updateName(name, user));

    // Salva as atualizações no banco de dados e o retorna.
    return this.usersRepository.save(user);

  }

  private User findUser(UUID id) throws UserNotFoundException {
    return this.usersRepository.findById(id).orElseThrow(UserNotFoundException::new);
  }

  private void checkIfEmailExists(String email) throws EmailAlreadyRegisteredException {
    if (this.usersRepository.findByEmail(email).isPresent()) {
      throw new EmailAlreadyRegisteredException();
    }
  }

  private void updateName(String name, User user) {
    user.setName(name);
  }

  private void updateEmail(String email, User user) throws EmailAlreadyRegisteredException {
    this.checkIfEmailExists(email);
    user.setEmail(email);
  }

}
