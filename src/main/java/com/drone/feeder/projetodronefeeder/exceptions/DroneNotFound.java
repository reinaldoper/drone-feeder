package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * Classe DroneException.
 **/

@SuppressWarnings("serial")
public class DroneNotFound extends RuntimeException {

  /** construtor . */
  public DroneNotFound() {
    super("Drone não existe");
  }

}
