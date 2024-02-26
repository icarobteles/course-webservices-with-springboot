package com.icarobteles.webservices.modules.users.application.usecases;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.icarobteles.webservices.modules.users.domain.exceptions.HashPasswordException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Caso de uso para fazer o hash da senha.
 */
@Service
public class HashPasswordUseCase {

  private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
  private static final int ITERATIONS = 65536;
  private static final int KEY_LENGTH = 128;

  public String execute(String password) throws HashPasswordException {
    try {
      byte[] salt = this.generateSalt();
      PBEKeySpec spec = this.createKeySpec(password, salt);
      var factory = this.getSecretKeyFactory();
      byte[] hash = this.generateHash(factory, spec);
      return this.encodeBase64(hash);
    } catch (Exception e) {
      var message = "Ocorreu algum erro interno durante o processamento de criptografia da senha";
      throw new HashPasswordException(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Gera um salt aleatório.
   */
  private byte[] generateSalt() {
    var random = new SecureRandom();
    var salt = new byte[16];
    random.nextBytes(salt);
    return salt;
  }

  /**
   * Cria uma especificação de chave baseada em senha para a geração de uma chave
   * secreta.
   */
  private PBEKeySpec createKeySpec(String password, byte[] salt) {
    return new PBEKeySpec(password.toCharArray(), salt, HashPasswordUseCase.ITERATIONS, HashPasswordUseCase.KEY_LENGTH);
  }

  /**
   * Retorna uma instância de SecretKeyFactory com base no algoritmo de hash.
   */
  private SecretKeyFactory getSecretKeyFactory() throws NoSuchAlgorithmException {
    return SecretKeyFactory.getInstance(HashPasswordUseCase.ALGORITHM);
  }

  /**
   * Gera a chave secreta com base na especificação de chave.
   */
  private byte[] generateHash(SecretKeyFactory factory, PBEKeySpec spec) throws InvalidKeySpecException {
    return factory.generateSecret(spec).getEncoded();
  }

  /**
   * Codifica o hash da senha em base64.
   */
  private String encodeBase64(byte[] hash) {
    return Base64.getEncoder().encodeToString(hash);
  }

}
