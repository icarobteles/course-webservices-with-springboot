package com.icarobteles.webservices.modules.users.domain.exceptions;

import org.springframework.http.HttpStatus;

import com.icarobteles.webservices.core.CoreHttpException;

public class HashPasswordException extends CoreHttpException {

  public HashPasswordException(String message, HttpStatus status) {
    super(message, status);
  }

}
