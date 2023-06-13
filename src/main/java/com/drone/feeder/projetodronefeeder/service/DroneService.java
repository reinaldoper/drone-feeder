package com.drone.feeder.projetodronefeeder.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.drone.feeder.projetodronefeeder.exceptions.DroneNotFound;
import com.drone.feeder.projetodronefeeder.model.Drone;
import com.drone.feeder.projetodronefeeder.model.Entrega;
import com.drone.feeder.projetodronefeeder.repository.DroneRepository;

/** classe droneService. */
@Service
public class DroneService {

  @Autowired
  DroneRepository droneRepo;

  /** metodo getAll. */
  public List<Drone> getAllDrones() {
    return droneRepo.findAll();
  }

  /** metdo getBYId. */
  public Drone getById(Integer id) {
    Drone droneId = droneRepo.findById(id).orElse(null);;
    if (droneId != null) {
      return droneId;
    } else {
      throw new DroneNotFound();
    }
  }

  /** metodo save. */
  public void save(Drone drone) {
    droneRepo.save(drone);
  }

  /** metdo update. */
  public void update(Drone drone, Integer id) {
    Drone droneId = droneRepo.findById(id).orElse(null);;
    if (droneId != null) {
      droneId.setLatitude(drone.getLatitude());
      droneId.setLongitude(drone.getLongitude());
      droneId.setEntrega(drone.getEntrega());
      droneRepo.save(droneId);
    } else {
      throw new DroneNotFound();
    }
  }

  /** metdo delete. */
  public void delete(Integer id) {
    Drone droneId = droneRepo.findById(id).orElse(null);;
    if (droneId != null) {
      droneRepo.deleteById(id);
    } else {
      throw new DroneNotFound();
    }
  }
}
