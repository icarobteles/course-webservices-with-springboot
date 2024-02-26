package com.icarobteles.webservices.shared.domain.exceptions;

import org.springframework.http.HttpStatus;

import com.icarobteles.webservices.core.CoreHttpException;

public class InvalidPropertyException extends CoreHttpException {
  public InvalidPropertyException(String message) {
    super(message, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
