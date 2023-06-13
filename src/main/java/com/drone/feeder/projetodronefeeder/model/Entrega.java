package com.drone.feeder.projetodronefeeder.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/** class Entrega. */

@Entity
public class Entrega {
  /** metodo. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime dataHora;
  private String status;
  // Outros atributos e getters/setters

  @ManyToOne
  @JoinColumn(name = "drone_id")
  private Drone drone;

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  /** construtor default. */
  public Entrega() {}

  /** construtor. */
  public Entrega(String status, Drone drone) {
    super();
    this.dataHora = LocalDateTime.now();
    this.status = status;
    this.drone = drone;
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
