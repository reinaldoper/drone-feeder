package com.drone.feeder.projetodronefeeder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String dataHora =
      LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
  private String status = "pendente";
  // Outros atributos e getters/setters

  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "drone_id")
  private Drone drone;


  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "video_id")
  private Video video;

  public String getDataHora() {
    return dataHora;
  }

  public Integer getId() {
    return id;
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
  public Entrega(String status, Video video, Drone drone) {
    super();
    this.status = status;
    this.video = video;
    this.drone = drone;
  }

  public void setDataHora(String dataHora) {
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

  /** metodo toString. */
  @Override
  public String toString() {
    return "Entrega{" + "dataHora='" + dataHora + '\'' + ", status='" + status + '\'' + ", drone="
        + drone + '}';
  }

}
