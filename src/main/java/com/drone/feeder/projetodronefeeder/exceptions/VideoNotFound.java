package com.drone.feeder.projetodronefeeder.exceptions;

/**
 * Classe VideoException.
 **/

@SuppressWarnings("serial")
public class VideoNotFound extends RuntimeException {

  /** construtor . */
  public VideoNotFound() {
    super("Video não existe");
  }
}
