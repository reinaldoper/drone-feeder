package com.drone.feeder.projetodronefeeder.service;

import com.drone.feeder.projetodronefeeder.exceptions.DroneNotFound;
import com.drone.feeder.projetodronefeeder.exceptions.DroneNull;
import com.drone.feeder.projetodronefeeder.model.Drone;
import com.drone.feeder.projetodronefeeder.repository.DroneRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    Double latitude = drone.getLatitude();
    Double longitude = drone.getLongitude();
    if (latitude == null || longitude == null) {
      throw new DroneNull();
    }
    droneRepo.save(drone);
  }

  /** metdo update. */
  public void update(Drone drone, Integer id) {
    Double latitude = drone.getLatitude();
    Double longitude = drone.getLongitude();
    if (latitude == null || longitude == null) {
      throw new DroneNull();
    }
    Drone droneId = droneRepo.findById(id).orElse(null);;
    if (droneId != null) {
      droneId.setLatitude(drone.getLatitude());
      droneId.setLongitude(drone.getLongitude());
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
