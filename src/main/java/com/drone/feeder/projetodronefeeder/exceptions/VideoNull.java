package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * Classe VideoException.
 **/

@SuppressWarnings("serial")
public class VideoNull extends RuntimeException {

  /** construtor . */
  public VideoNull() {
    super("O campo /nomeArquivo/ não pode ser nulo.");
  }
}
