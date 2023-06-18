package com.drone.feeder.projetodronefeeder.controller;

import com.drone.feeder.projetodronefeeder.exceptions.DroneNotFound;
import com.drone.feeder.projetodronefeeder.exceptions.DroneNull;
import com.drone.feeder.projetodronefeeder.exceptions.EntregaNotFound;
import com.drone.feeder.projetodronefeeder.exceptions.EntregaNull;
import com.drone.feeder.projetodronefeeder.exceptions.InternalException;
import com.drone.feeder.projetodronefeeder.exceptions.StatusError;
import com.drone.feeder.projetodronefeeder.exceptions.VideoNotFound;
import com.drone.feeder.projetodronefeeder.exceptions.VideoNull;
import com.drone.feeder.projetodronefeeder.exceptions.VideoRelacionadoEntrega;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 * 
 * ControllerAdvice.
 */

@ControllerAdvice
public class GerenciadorAdvice {

  /** metodo video error. */
  @ExceptionHandler(VideoNotFound.class)
  public ResponseEntity<HashMap<String, String>> handleVideoException(VideoNotFound ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
  }

  /** metodo video null. */
  @ExceptionHandler(VideoNull.class)
  public ResponseEntity<HashMap<String, String>> handleVideoNullException(VideoNull ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(msg);
  }

  /** metodo entrega null. */
  @ExceptionHandler(EntregaNull.class)
  public ResponseEntity<HashMap<String, String>> handleEntregaNullException(EntregaNull ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(msg);
  }

  /** metodo video null. */
  @ExceptionHandler(VideoRelacionadoEntrega.class)
  public ResponseEntity<HashMap<String, String>> handleVideoRelacionaException(
      VideoRelacionadoEntrega ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
  }

  /** metodo Drone error. */
  @ExceptionHandler(DroneNotFound.class)
  public ResponseEntity<HashMap<String, String>> handleDroneException(DroneNotFound ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
  }

  /** metodo Drone null. */
  @ExceptionHandler(DroneNull.class)
  public ResponseEntity<HashMap<String, String>> handleDroneNullException(DroneNull ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(msg);
  }

  /** metodo Entrega error. */
  @ExceptionHandler(EntregaNotFound.class)
  public ResponseEntity<HashMap<String, String>> handleEntregaException(EntregaNotFound ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
  }

  /** metodo altera status error. */
  @ExceptionHandler(StatusError.class)
  public ResponseEntity<HashMap<String, String>> handleStatusException(StatusError ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
  }

  /** error interno. */
  @ExceptionHandler(InternalException.class)
  public ResponseEntity<HashMap<String, String>> handleException(InternalException ex) {
    HashMap<String, String> msg = new HashMap<String, String>();
    msg.put("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
  }
}
