package com.drone.feeder.projetodronefeeder.service;

import com.drone.feeder.projetodronefeeder.controller.AdicionaTarefa;
import com.drone.feeder.projetodronefeeder.controller.UpdateTarefa;
import com.drone.feeder.projetodronefeeder.exceptions.DroneNotFound;
import com.drone.feeder.projetodronefeeder.exceptions.EntregaNotFound;
import com.drone.feeder.projetodronefeeder.exceptions.EntregaNull;
import com.drone.feeder.projetodronefeeder.exceptions.InternalException;
import com.drone.feeder.projetodronefeeder.exceptions.StatusError;
import com.drone.feeder.projetodronefeeder.exceptions.VideoNotFound;
import com.drone.feeder.projetodronefeeder.model.Drone;
import com.drone.feeder.projetodronefeeder.model.Entrega;
import com.drone.feeder.projetodronefeeder.model.Video;
import com.drone.feeder.projetodronefeeder.repository.DroneRepository;
import com.drone.feeder.projetodronefeeder.repository.EntregaRepository;
import com.drone.feeder.projetodronefeeder.repository.VideoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * classe entregaService.
 */
@Service
@Transactional
public class EntregaService {

  @Autowired
  EntregaRepository entregaRepo;

  @Autowired
  DroneRepository droneRepo;

  @Autowired
  VideoRepository videoRepo;

  /**
   * metodo getAllEntregas.
   */
  public List<Entrega> getAllEntregasDrone(Integer id) {
    Drone droneId = droneRepo.findById(id).orElse(null);
    if (droneId != null) {
      return droneId.getEntregas();
    } else {
      throw new DroneNotFound();
    }
  }

  /**
   * metodo getAll.
   */

  public List<Entrega> getAllEntregas() {
    return entregaRepo.findAll();
  }

  /**
   * metodo getById.
   */
  public Entrega getById(Integer id) {
    Entrega entrega = entregaRepo.findById(id).orElse(null);
    if (entrega != null) {
      return entrega;
    } else {
      throw new EntregaNotFound();
    }
  }

  /**
   * metodo deleteById.
   */

  public void deleteById(Integer id) {
    Entrega entrega = entregaRepo.findById(id).orElse(null);
    System.out.println(entrega);
    if (entrega != null) {
      Drone drone = entrega.getDrone();
      drone.deletaEntrega(entrega);
      entregaRepo.delete(entrega);
    } else {
      throw new EntregaNotFound();
    }
  }

  /**
   * metodo save.
   */

  public void save(AdicionaTarefa adicionaTarefa) {
    if (!adicionaTarefa.getStatus().equals("pendente")
        && !adicionaTarefa.getStatus().equals("entregue")) {
      throw new StatusError();
    }
    if (adicionaTarefa.getVideoId() == null || adicionaTarefa.getDroneId() == null) {
      throw new EntregaNull();
    }
    try {
      Integer ids = adicionaTarefa.getVideoId();
      Video video = videoRepo.findById(ids).orElse(null);
      if (video == null) {
        throw new VideoNotFound();
      }
      Integer droneId = adicionaTarefa.getDroneId();
      Drone drone = droneRepo.findById(droneId).orElse(null);
      if (drone == null) {
        throw new DroneNotFound();
      }
      Entrega entrega = new Entrega();
      entrega.setStatus(adicionaTarefa.getStatus());
      entrega.setVideo(video);
      entrega.setDrone(drone);
      drone.adicionarEntrega(entrega);
      droneRepo.save(drone);
    } catch (NumberFormatException e) {
      throw new InternalException();
    }

  }

  /**
   * metodo update.
   */

  public void update(Integer id, UpdateTarefa updateTarefa) {
    if (updateTarefa.getVideoId() == null) {
      throw new EntregaNull();
    }
    Entrega entrega = entregaRepo.findById(id).orElse(null);
    if (entrega != null) {
      Integer ids = updateTarefa.getVideoId();
      Video video = videoRepo.findById(ids).orElse(null);
      if (video == null) {
        throw new VideoNotFound();
      }
      entrega.setVideo(video);
      entregaRepo.save(entrega);
    } else {
      throw new EntregaNotFound();
    }
  }

  /**
   * metodo alterar status.
   */
  public void atualizaEntrega(Integer id, AdicionaTarefa adicionaTarefa) {
    if (adicionaTarefa.getStatus() == null
        || adicionaTarefa.getDroneId() == null || adicionaTarefa.getVideoId() == null) {
      throw new EntregaNotFound();
    }
    boolean ids = videoRepo.existsById(adicionaTarefa.getVideoId());
    Drone drone = droneRepo.findById(adicionaTarefa.getDroneId()).orElse(null);
    Video video = videoRepo.findById(adicionaTarefa.getVideoId()).orElse(null);
    Entrega entrega = entregaRepo.findById(id).orElse(null);
    if (!ids || drone == null || entrega == null || video == null) {
      throw new EntregaNotFound();
    }
    entrega.setDrone(drone);
    entrega.setVideo(video);
    entrega.setStatus(adicionaTarefa.getStatus());
  }

  /**
   * metodo alterar status.
   */
  public void status(String status, Integer id) {
    if (!status.equals("pendente") && !status.equals("entregue")) {
      throw new StatusError();
    }
    Entrega entrega = entregaRepo.findById(id).orElse(null);
    if (entrega != null) {
      entrega.setStatus(status);
      entregaRepo.save(entrega);
    } else {
      throw new EntregaNotFound();
    }
  }

}
