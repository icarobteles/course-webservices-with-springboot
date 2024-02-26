package com.icarobteles.webservices.core;

import jakarta.validation.constraints.NotBlank;

public record CoreHttpExceptionResponse(
    @NotBlank
    String message
) {}
