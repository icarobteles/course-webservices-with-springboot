package com.icarobteles.webservices.core;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CoreHttpException extends RuntimeException {
  private final HttpStatus status;

  public CoreHttpException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

}
