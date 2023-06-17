package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * Classe StatusException.
 **/

@SuppressWarnings("serial")
public class StatusError extends RuntimeException {

  /** construtor . */
  public StatusError() {
    super("O status deve ser /pendente/ ou /entregue/");
  }
}
