package com.drone.feeder.projetodronefeeder.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/** class controller. */
@Entity
public class Drone {

  /** metodos. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private double latitude;
  private double longitude;

  @JsonManagedReference
  @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true,
      fetch = FetchType.EAGER)
  private List<Entrega> entrega;

  public double getLatitude() {
    return latitude;
  }

  public List<Entrega> getEntrega() {
    return entrega;
  }

  public void setEntrega(List<Entrega> entrega) {
    this.entrega = entrega;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public Integer getId() {
    return id;
  }

  public double getLongitude() {
    return longitude;
  }

  /** constructor. */
  public Drone() {}

  /** construtor default. */
  public Drone(double latitude, double longitude) {
    super();
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  /** metodo adicionar. */
  public void adicionarEntrega(Entrega entrega) {
    this.entrega.add(entrega);

  }

}
