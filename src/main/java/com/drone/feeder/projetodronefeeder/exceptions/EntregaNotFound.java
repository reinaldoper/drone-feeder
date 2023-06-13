package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * Classe EntregaException.
 **/

@SuppressWarnings("serial")
public class EntregaNotFound extends RuntimeException {

  /** construtor . */
  public EntregaNotFound() {
    super("Entrega não existe");
  }
}
