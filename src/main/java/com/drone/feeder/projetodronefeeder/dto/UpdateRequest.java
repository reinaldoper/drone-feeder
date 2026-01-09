package com.drone.feeder.projetodronefeeder.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * classe dto.
 */
public class UpdateRequest {

  /**
   * metodos.
   */
  @Email(message = "E-mail inválido")
  @NotBlank(message = "E-mail é obrigatório")
  private String email;

  @NotBlank(message = "Senha é obrigatória")
  private String password;

  @NotBlank(message = "Nome é obrigatório")
  private String name;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
