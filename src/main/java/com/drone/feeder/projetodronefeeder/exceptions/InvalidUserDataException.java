package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * classe exceptions invalid user.
 */
public class InvalidUserDataException extends RuntimeException {
  public InvalidUserDataException(String message) {
    super(message);
  }
}
