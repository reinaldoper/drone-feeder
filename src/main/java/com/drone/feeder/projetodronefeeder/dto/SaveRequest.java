package com.drone.feeder.projetodronefeeder.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO para requisição de login.
 */
public class SaveRequest {

  /**
   * metodos.
   */
  @Email(message = "E-mail inválido")
  @NotBlank(message = "E-mail é obrigatório")
  private String email;

  @NotBlank(message = "Senha é obrigatória")
  @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
  private String password;

  @NotBlank(message = "Nome é obrigatório")
  @Size(min = 1, message = "Nome não pode estar vazio")
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
