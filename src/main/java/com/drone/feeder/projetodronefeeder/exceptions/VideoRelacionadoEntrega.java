package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * Classe VideoException.
 **/

@SuppressWarnings("serial")
public class VideoRelacionadoEntrega extends RuntimeException {

  /** constructor. */
  public VideoRelacionadoEntrega() {
    super("Video relacionado com entrega, não pode ser excluido.");
  }
}
