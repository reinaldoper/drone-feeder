package com.drone.feeder.projetodronefeeder.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.drone.feeder.projetodronefeeder.exceptions.DroneNotFound;
import com.drone.feeder.projetodronefeeder.exceptions.EntregaNotFound;
import com.drone.feeder.projetodronefeeder.model.Drone;
import com.drone.feeder.projetodronefeeder.model.Entrega;
import com.drone.feeder.projetodronefeeder.repository.DroneRepository;
import com.drone.feeder.projetodronefeeder.repository.EntregaRepository;

/** classe entregaService. */
@Service
public class EntregaService {

  @Autowired
  EntregaRepository entregaRepo;

  @Autowired
  DroneRepository droneRepo;

  /** metodo getAllEntregas. */
  public List<Entrega> getAllEntregasDrone(Long id) {
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
  public Entrega getById(Long id) {
    Entrega entrega = entregaRepo.findById(id).orElse(null);
    if (entrega != null) {
      return entrega;
    } else {
      throw new EntregaNotFound();
    }
  }

  /** metodo deleteById. */
  public void deleteById(Long id) {
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

  /** metodo update. */
  public void update(Entrega entrega, Long id) {
    Entrega entregaId = entregaRepo.findById(id).orElse(null);
    if (entregaId != null) {
      entregaId.setDataHora(entrega.getDataHora());
      entregaId.setDrone(entrega.getDrone());
      entregaRepo.save(entregaId);
    } else {
      throw new EntregaNotFound();
    }
  }

}
