package com.drone.feeder.projetodronefeeder.controller;

import com.drone.feeder.projetodronefeeder.exceptions.InternalException;
import com.drone.feeder.projetodronefeeder.model.Drone;
import com.drone.feeder.projetodronefeeder.service.DroneService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** class droneController. */

@RestController
@RequestMapping("/drones")
public class DroneController {

  @Autowired
  DroneService droneService;

  /** metodo getAllDrones. */
  @CrossOrigin
  @GetMapping
  public ResponseEntity<List<Drone>> getAllDrones() {
    List<Drone> drones = droneService.getAllDrones();
    return ResponseEntity.status(HttpStatus.OK).body(drones);
  }

  /** metodo getDroneId. */
  @CrossOrigin
  @GetMapping("/{id}")
  public ResponseEntity<Drone> getDroneById(@PathVariable String id) {
    try {
      Integer parsedId = Integer.parseInt(id);
      Drone drone = droneService.getById(parsedId);
      return ResponseEntity.status(HttpStatus.OK).body(drone);
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

  /** metodo saveDrone. */
  @CrossOrigin
  @PostMapping
  public ResponseEntity<HashMap<String, String>> createDrone(@RequestBody Drone drone) {
    droneService.save(drone);
    return ResponseEntity.status(HttpStatus.CREATED).body(new MensagemController().mensagem());
  }

  /** metodo updateDrone. */
  @CrossOrigin
  @PutMapping("/{id}")
  public ResponseEntity<HashMap<String, String>> updateDrone(@PathVariable String id,
      @RequestBody Drone updatedDrone) {
    try {
      Integer parsedId = Integer.parseInt(id);
      droneService.update(updatedDrone, parsedId);
      return ResponseEntity.status(HttpStatus.OK).body(new MensagemController().atualizar());
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

  /** metodo deleteDrone. */
  @CrossOrigin
  @DeleteMapping("/{id}")
  public ResponseEntity<HashMap<String, String>> deleteDrone(@PathVariable String id) {
    try {
      Integer parsedId = Integer.parseInt(id);
      droneService.delete(parsedId);
      return ResponseEntity.status(HttpStatus.OK).body(new MensagemController().excluir());
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

}
