package com.drone.feeder.projetodronefeeder.controller;

/** class update tarefa. */
public class UpdateTarefa {
  /** metodos. */
  private Integer videoId;

  public Integer getVideoId() {
    return videoId;
  }

  public void setVideoId(Integer videoId) {
    this.videoId = videoId;
  }

  /** construtor. */
  public UpdateTarefa() {
    super();
  }

  /** construtor. */
  public UpdateTarefa(Integer videoId) {
    super();
    this.videoId = videoId;
  }
}
