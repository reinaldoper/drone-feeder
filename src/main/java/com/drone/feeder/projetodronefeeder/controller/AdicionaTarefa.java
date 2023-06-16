package com.drone.feeder.projetodronefeeder.controller;


/** adiciona. */
public class AdicionaTarefa {
  /** metodos. */
  private String status;
  private Integer droneId;
  private Integer videoId;

  public Integer getDroneId() {
    return droneId;
  }

  public void setDroneId(Integer droneId) {
    this.droneId = droneId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  /** constructor. */
  public AdicionaTarefa(String status, Integer droneId, Integer videoId) {
    super();
    this.status = status;
    this.droneId = droneId;
    this.videoId = videoId;
  }

  public Integer getVideoId() {
    return videoId;
  }

  public void setVideoId(Integer videoId) {
    this.videoId = videoId;
  }
}
