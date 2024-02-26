package com.icarobteles.webservices.modules.users.domain.exceptions;

import org.springframework.http.HttpStatus;

import com.icarobteles.webservices.core.CoreHttpException;

public class UserNotFoundException extends CoreHttpException {

  public UserNotFoundException() {
    super("Usuário não encontrado", HttpStatus.NOT_FOUND);
  }

}
