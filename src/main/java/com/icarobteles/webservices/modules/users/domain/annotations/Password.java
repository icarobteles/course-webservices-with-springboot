package com.icarobteles.webservices.modules.users.domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = {})
@Pattern(regexp = "(?=.*\\d).+", message = "A senha deve ter pelo menos um número")
@Pattern(regexp = "(?=.*[a-z]).+", message = "A senha deve ter pelo menos uma letra minúscula")
@Pattern(regexp = "(?=.*[A-Z]).+", message = "A senha deve ter pelo menos uma letra maiúscula")
@Pattern(regexp = "(?=.*[@#$%^&+=]).+", message = "A senha deve conter pelo menos um caractere especial")
@Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
public @interface Password {
  String message() default "Senha inválida";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
