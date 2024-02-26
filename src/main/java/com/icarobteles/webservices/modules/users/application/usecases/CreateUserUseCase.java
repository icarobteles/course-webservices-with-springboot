package com.icarobteles.webservices.modules.users.application.usecases;

import org.springframework.stereotype.Service;

import com.icarobteles.webservices.modules.users.application.dtos.CreateUserDTO;
import com.icarobteles.webservices.modules.users.domain.entities.User;
import com.icarobteles.webservices.modules.users.domain.exceptions.EmailAlreadyRegisteredException;
import com.icarobteles.webservices.modules.users.domain.exceptions.HashPasswordException;
import com.icarobteles.webservices.modules.users.domain.repositories.UsersRepository;

import lombok.AllArgsConstructor;

/**
 * Caso de uso para criar um novo usuário.
 */
@AllArgsConstructor
@Service
public class CreateUserUseCase {
  private final UsersRepository usersRepository;
  private final HashPasswordUseCase hashPasswordUseCase;

  public User execute(CreateUserDTO data) throws EmailAlreadyRegisteredException, HashPasswordException {
    String name = data.name();
    String email = data.email();
    String password = data.password();

    // Verifica se o email já está em uso.
    this.checkIfEmailExists(email);

    // Faz o hash da senha.
    String hashedPassword = this.hashPassword(password);

    // Cria o novo usuário.
    User user = new User(name, email, hashedPassword);

    // Salva o novo usuário no banco de dados e o retorna.
    return this.usersRepository.save(user);

  }

  private void checkIfEmailExists(String email) throws EmailAlreadyRegisteredException {
    if (this.usersRepository.findByEmail(email).isPresent()) {
      throw new EmailAlreadyRegisteredException();
    }
  }

  private String hashPassword(String password) throws HashPasswordException {
    return this.hashPasswordUseCase.execute(password);
  }

}
