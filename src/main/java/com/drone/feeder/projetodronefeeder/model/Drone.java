package com.drone.feeder.projetodronefeeder.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** class controller. */
@Entity
public class Drone {

  /** metodos. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private double latitude;
  private double longitude;

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  /** construtor default. */
  public Drone() {}

  /** constructor. */
  public Drone(double latitude, double longitude) {
    super();
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

}
