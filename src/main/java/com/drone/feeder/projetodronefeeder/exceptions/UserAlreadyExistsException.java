package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * classe exceptions user already exist.
 */
public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
