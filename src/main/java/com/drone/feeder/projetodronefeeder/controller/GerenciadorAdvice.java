package com.drone.feeder.projetodronefeeder.controller;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.drone.feeder.projetodronefeeder.exceptions.DroneNotFound;
import com.drone.feeder.projetodronefeeder.exceptions.EntregaNotFound;
import com.drone.feeder.projetodronefeeder.exceptions.VideoNotFound;

/*
 * 
 * ControllerAdvice.
 */

@ControllerAdvice
public class GerenciadorAdvice {

  /** metodo video. */
  @ExceptionHandler(VideoNotFound.class)
  public ResponseEntity<HashMap<String, String>> handleVideoException(VideoNotFound ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
  }

  /** metodo Drone. */
  @ExceptionHandler(DroneNotFound.class)
  public ResponseEntity<HashMap<String, String>> handleDroneException(DroneNotFound ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
  }

  /** metodo Entrega. */
  @ExceptionHandler(EntregaNotFound.class)
  public ResponseEntity<HashMap<String, String>> handleEntregaException(EntregaNotFound ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
  }
}
