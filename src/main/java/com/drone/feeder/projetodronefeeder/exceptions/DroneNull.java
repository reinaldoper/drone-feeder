package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * Classe DroneException.
 **/

@SuppressWarnings("serial")
public class DroneNull extends RuntimeException {

  /** construtor . */
  public DroneNull() {
    super("Latitude ou longitude precisam ser preenchidos.");
  }
}
