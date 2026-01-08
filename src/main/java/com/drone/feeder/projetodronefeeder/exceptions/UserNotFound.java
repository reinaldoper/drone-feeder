package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * Classe UserException.
 **/

public class UserNotFound extends RuntimeException {
  public UserNotFound(String message) {
    super(message);
  }
}
