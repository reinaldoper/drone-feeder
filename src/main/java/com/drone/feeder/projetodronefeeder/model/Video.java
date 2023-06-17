package com.drone.feeder.projetodronefeeder.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/** class Video. */

@Entity
public class Video {

  /** metodo. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nomeArquivo;

  @OneToOne(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true,
      fetch = FetchType.EAGER)
  private Entrega entrega;

  public String getNomeArquivo() {
    return nomeArquivo;
  }

  public Integer getId() {
    return id;
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
