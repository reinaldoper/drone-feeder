package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * Classe VideoException.
 **/

@SuppressWarnings("serial")
public class RelacionadoEntrega extends RuntimeException {

  /** constructor. */
  public RelacionadoEntrega() {
    super("Relacionado com entrega, não pode ser excluido.");
  }
}
