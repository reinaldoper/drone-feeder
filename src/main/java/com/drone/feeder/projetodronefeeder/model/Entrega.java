package com.drone.feeder.projetodronefeeder.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/** class Entrega. */

@Entity
public class Entrega {
  /** metodo. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private LocalDateTime dataHora;
  private String status = "entregue";
  // Outros atributos e getters/setters

  @ManyToOne
  @JoinColumn(name = "drone_id")
  private Drone drone;

  @OneToOne(fetch = FetchType.LAZY)
  private Video video;

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  public Video getVideo() {
    return video;
  }

  public void setVideo(Video video) {
    this.video = video;
  }

  /** construtor default. */
  public Entrega() {}

  /** construtor. */
  public Entrega(String status, Drone drone, Video video) {
    super();
    this.dataHora = LocalDateTime.now();
    this.status = status;
    this.drone = drone;
    this.video = video;
  }

  public void setDataHora(LocalDateTime dataHora) {
    this.dataHora = dataHora;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Drone getDrone() {
    return drone;
  }

  public void setDrone(Drone drone) {
    this.drone = drone;
  }

}
