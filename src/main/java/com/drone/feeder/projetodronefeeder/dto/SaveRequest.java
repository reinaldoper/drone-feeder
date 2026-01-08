package com.drone.feeder.projetodronefeeder.dto;

/**
 * DTO para requisição de login.
 */
public class SaveRequest {

  /**
   * metodos.
   */
  private String email;
  private String password;
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
