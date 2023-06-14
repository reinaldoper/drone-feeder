package com.drone.feeder.projetodronefeeder.controller;

import com.drone.feeder.projetodronefeeder.exceptions.InternalException;
import com.drone.feeder.projetodronefeeder.model.Entrega;
import com.drone.feeder.projetodronefeeder.service.EntregaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** class entregaController. */
/** class droneController. */

@RestController
@RequestMapping("/entregas")
public class EntregaController {

  @Autowired
  EntregaService entregaService;

  /** metodo getAllEntregas. */
  @GetMapping
  public ResponseEntity<List<Entrega>> getAllEntregas() {
    try {
      List<Entrega> entregas = entregaService.getAllEntregas();
      return ResponseEntity.status(HttpStatus.OK).body(entregas);
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

  /** metodo getIdEntregasDrones. */
  @GetMapping("/drones/{id}")
  public ResponseEntity<List<Entrega>> getIdDrones(@PathVariable Integer id) {
    try {
      List<Entrega> entregas = entregaService.getAllEntregasDrone(id);
      return ResponseEntity.status(HttpStatus.OK).body(entregas);
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

  /** metodo getIdEntregas. */
  @GetMapping("/{id}")
  public ResponseEntity<Entrega> getById(@PathVariable Integer id) {
    try {
      Entrega entrega = entregaService.getById(id);
      return ResponseEntity.status(HttpStatus.OK).body(entrega);
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

}
