package com.icarobteles.webservices.shared.infrastructure.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.icarobteles.webservices.core.CoreHttpException;
import com.icarobteles.webservices.core.CoreHttpExceptionResponse;

@RestControllerAdvice
public class HttpExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CoreHttpExceptionResponse> handleValidationExceptions(
      MethodArgumentNotValidException exception) {
    FieldError primaryError = exception.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);

    if (primaryError != null) {
      var errorResponse = new CoreHttpExceptionResponse(primaryError.getDefaultMessage());
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    var errorResponse = new CoreHttpExceptionResponse("Algum dado está inválido");
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);

  }

  @ExceptionHandler(CoreHttpException.class)
  public ResponseEntity<CoreHttpExceptionResponse> handleHttpException(CoreHttpException exception) {
    var errorResponse = new CoreHttpExceptionResponse(exception.getMessage());
    return ResponseEntity.status(exception.getStatus()).body(errorResponse);
  }
}
