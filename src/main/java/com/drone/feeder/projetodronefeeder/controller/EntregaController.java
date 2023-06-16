package com.drone.feeder.projetodronefeeder.controller;

import com.drone.feeder.projetodronefeeder.exceptions.InternalException;
import com.drone.feeder.projetodronefeeder.model.Drone;
import com.drone.feeder.projetodronefeeder.model.Entrega;
import com.drone.feeder.projetodronefeeder.model.Video;
import com.drone.feeder.projetodronefeeder.service.EntregaService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  /** altera status. */
  @PutMapping("/{id}/{status}")
  public ResponseEntity<HashMap<String, String>> atualizaStatus(@PathVariable String id,
      @PathVariable String status) {
    try {
      Integer parsedId = Integer.parseInt(id);
      entregaService.status(status, parsedId);
      return ResponseEntity.status(HttpStatus.OK).body(new MensagemController().atualizar());
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

  /** metodo getIdEntregas. */
  @GetMapping("/{id}")
  public ResponseEntity<Entrega> getById(@PathVariable String id) {
    try {
      Integer parsedId = Integer.parseInt(id);
      Entrega entrega = entregaService.getById(parsedId);
      return ResponseEntity.status(HttpStatus.OK).body(entrega);
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

  /** deleta entrega. */
  @DeleteMapping("/{id}")
  public ResponseEntity<HashMap<String, String>> deleteEntrega(@PathVariable String id) {
    try {
      Integer parsedId = Integer.parseInt(id);
      entregaService.deleteById(parsedId);
      return ResponseEntity.status(HttpStatus.OK).body(new MensagemController().excluir());
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

  /** adiciona tarefa nova para um drone . */
  @PostMapping
  public ResponseEntity<HashMap<String, String>> entregaSave(
      @RequestBody AdicionaTarefa adicionaTarefa) {
    entregaService.save(adicionaTarefa);
    return ResponseEntity.status(HttpStatus.CREATED).body(new MensagemController().mensagem());
  }

  /** altera tarefa nova para um drone . */
  @PutMapping("/{id}")
  public ResponseEntity<HashMap<String, String>> entregaUpdate(@PathVariable String id,
      @RequestBody AdicionaTarefa adicionaTarefa) {
    try {
      Integer parsedId = Integer.parseInt(id);
      entregaService.update(parsedId, adicionaTarefa);
      return ResponseEntity.status(HttpStatus.OK).body(new MensagemController().excluir());
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

}
