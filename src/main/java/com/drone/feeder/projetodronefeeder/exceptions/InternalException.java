package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * Classe Exception.
 **/

@SuppressWarnings("serial")
public class InternalException extends RuntimeException {
  /** metodo super. */
  public InternalException() {
    super("Erro interno");
  }

}
