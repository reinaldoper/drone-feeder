package com.drone.feeder.projetodronefeeder.service;

import com.drone.feeder.projetodronefeeder.exceptions.DroneNotFound;
import com.drone.feeder.projetodronefeeder.exceptions.EntregaNotFound;
import com.drone.feeder.projetodronefeeder.model.Drone;
import com.drone.feeder.projetodronefeeder.model.Entrega;
import com.drone.feeder.projetodronefeeder.repository.DroneRepository;
import com.drone.feeder.projetodronefeeder.repository.EntregaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** classe entregaService. */
@Service
public class EntregaService {

  @Autowired
  EntregaRepository entregaRepo;

  @Autowired
  DroneRepository droneRepo;

  /** metodo getAllEntregas. */
  public List<Entrega> getAllEntregasDrone(Integer id) {
    Drone droneId = droneRepo.findById(id).orElse(null);;
    if (droneId != null) {
      return droneId.getEntrega();
    } else {
      throw new DroneNotFound();
    }
  }

  /** metodo getAll. */

  public List<Entrega> getAllEntregas() {
    return entregaRepo.findAll();
  }

  /** metodo getById. */
  public Entrega getById(Integer id) {
    Entrega entrega = entregaRepo.findById(id).orElse(null);
    if (entrega != null) {
      return entrega;
    } else {
      throw new EntregaNotFound();
    }
  }

  /** metodo deleteById. */
  public void deleteById(Integer id) {
    Entrega entrega = entregaRepo.findById(id).orElse(null);
    if (entrega != null) {
      entregaRepo.delete(entrega);
    } else {
      throw new EntregaNotFound();
    }
  }

  /** metodo save. */

  public void save(Entrega entrega) {
    entregaRepo.save(entrega);
  }

  /** metodo alterar status. */
  public void status(String status, Integer id) {
    Entrega entrega = entregaRepo.findById(id).orElse(null);
    if (entrega != null) {
      entrega.setStatus(status);
    } else {
      throw new EntregaNotFound();
    }
  }

  /** metodo update. */
  public void update(Entrega entrega, Integer id) {
    Entrega entregaId = entregaRepo.findById(id).orElse(null);
    if (entregaId != null) {
      entregaId.setDataHora(entrega.getDataHora());
      entregaId.setVideo(entrega.getVideo());
      entregaRepo.save(entregaId);
    } else {
      throw new EntregaNotFound();
    }
  }

  /** salva entrega drone. */
  public void saveEntregaDrone(Entrega entrega, Integer id) {
    Drone droneId = droneRepo.findById(id).orElse(null);
    if (droneId != null) {
      entrega.setDrone(droneId);
      droneId.adicionarEntrega(entrega);
      droneRepo.save(droneId);
    } else {
      throw new DroneNotFound();
    }
  }

}
