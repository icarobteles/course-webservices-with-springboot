package com.icarobteles.webservices.shared.domain.exceptions;

import org.springframework.http.HttpStatus;

import com.icarobteles.webservices.core.CoreHttpException;

public class BodyRequestEmptyException extends CoreHttpException {

  public BodyRequestEmptyException(String message) {
    super(BodyRequestEmptyException.handleMessage(message), HttpStatus.BAD_REQUEST);
  }

  private static String handleMessage(String message) {
    return message.isEmpty() ? "O corpo da requisição não pode estar vazio" : message;
  }

}
