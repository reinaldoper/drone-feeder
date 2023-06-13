package com.drone.feeder.projetodronefeeder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** class Video. */

@Entity
public class Video {

  /** metodo. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nomeArquivo;

  public String getNomeArquivo() {
    return nomeArquivo;
  }

  /** construtor. */
  public Video(String nomeArquivo) {
    super();
    this.nomeArquivo = nomeArquivo;
  }

  /** construtor default. */
  public Video() {
    super();
  }

  public void setNomeArquivo(String nomeArquivo) {
    this.nomeArquivo = nomeArquivo;
  }
}
