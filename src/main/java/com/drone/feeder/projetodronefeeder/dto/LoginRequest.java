package com.drone.feeder.projetodronefeeder.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * DTO para requisição de login.
 */
public class LoginRequest {

  /**
   * metodos.
   */
  @Email(message = "E-mail inválido")
  @NotBlank(message = "E-mail é obrigatório")
  private String email;

  @NotBlank(message = "Senha é obrigatória")
  private String password;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
