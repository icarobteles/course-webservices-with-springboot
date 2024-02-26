package com.icarobteles.webservices.modules.users.application.dtos;

import com.icarobteles.webservices.modules.users.domain.annotations.Password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
    @NotBlank(message = "O nome é obrigatório") 
    String name,

    @NotBlank(message = "O email é obrigatório") 
    @Email(message = "O email deve ter um endereço válido") 
    String email,

    @NotBlank(message = "A senha é obrigatória") 
    @Password 
    String password
) {
}
