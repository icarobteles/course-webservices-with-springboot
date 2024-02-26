package com.icarobteles.webservices.modules.users.domain.exceptions;

import org.springframework.http.HttpStatus;

import com.icarobteles.webservices.core.CoreHttpException;

public class EmailAlreadyRegisteredException extends CoreHttpException {

  public EmailAlreadyRegisteredException() {
    super("Este email já está em uso", HttpStatus.CONFLICT);
  }

}
