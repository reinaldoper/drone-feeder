package com.drone.feeder.projetodronefeeder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * class userModel.
 */
@Entity
public class User {

  /**
   * metodos.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private String password;
  private String email;

  public User() {
  }

  /**
   * metodo construtor do usuario.
   */
  public User(String name, String password, String email) {
    super();
    this.email = email;
    this.password = password;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getId() {
    return id;
  }
}
