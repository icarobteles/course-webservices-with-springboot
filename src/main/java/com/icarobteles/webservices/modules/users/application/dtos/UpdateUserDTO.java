package com.icarobteles.webservices.modules.users.application.dtos;

import java.util.Optional;

import com.icarobteles.webservices.shared.domain.exceptions.BodyRequestEmptyException;
import com.icarobteles.webservices.shared.domain.exceptions.InvalidPropertyException;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UpdateUserDTO {

  private final Optional<String> name;
  private final Optional<String> email;

  public UpdateUserDTO(@NotEmpty String name, @NotEmpty @Email String email) {

    if (name == null && email == null) {
      throw new BodyRequestEmptyException("Ao menos um das propriedades devem ser atualizadas");
    }

    if (name != null && name.isBlank()) {
      throw new InvalidPropertyException("O nome não pode ser vazio");
    }

    if (email != null) {

      if (email.isBlank()) {
        throw new InvalidPropertyException("O email não pode ser vazio");
      }

      if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
        throw new InvalidPropertyException("O email informado é inválido");
      }
    }

    this.name = this.parseOptional(name);
    this.email = this.parseOptional(email);
  }

  private <T extends Object> Optional<T> parseOptional(T value) {
    return value == null ? Optional.empty() : Optional.of(value);
  }

  public Optional<String> name() {
    return this.name;
  }

  public Optional<String> email() {
    return this.email;
  }

}
