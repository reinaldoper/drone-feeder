package com.drone.feeder.projetodronefeeder.exceptions;


/**
 * Classe EntregaException.
 **/

@SuppressWarnings("serial")
public class EntregaNull extends RuntimeException {

  /** constructor. */
  public EntregaNull() {
    super("Campos não pode ser null.");
  }

}
