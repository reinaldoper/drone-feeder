package com.drone.feeder.projetodronefeeder.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * class Video.
 */

@Entity
public class Video {

  /**
   * metodo.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "A URL do vídeo não pode estar vazia")
  @Pattern(regexp = "^(https?://)?([\\w.-]+)(:[0-9]+)?(/.*)?$", message = "URL inválida.")
  private String url;

  public String getUrl() {
    return url;
  }

  public Integer getId() {
    return id;
  }

  /**
   * construtor.
   */
  public Video(String url) {
    super();
    this.url = url;
  }

  /**
   * construtor default.
   */
  public Video() {
    super();
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
