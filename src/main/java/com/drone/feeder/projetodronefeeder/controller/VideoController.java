package com.drone.feeder.projetodronefeeder.controller;

import com.drone.feeder.projetodronefeeder.exceptions.InternalException;
import com.drone.feeder.projetodronefeeder.model.Video;
import com.drone.feeder.projetodronefeeder.service.VideoService;
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

/** class videoController. */

@RestController
@RequestMapping("/videos")
public class VideoController {

  @Autowired
  VideoService videoService;

  /** metodo getAllVideos. */
  @GetMapping
  public ResponseEntity<List<Video>> getAllVideos() {
    List<Video> video = videoService.getAllVideos();
    return ResponseEntity.status(HttpStatus.OK).body(video);
  }

  /** metodo getByIdVideo. */
  @GetMapping("/{id}")
  public ResponseEntity<Video> getByIdVideo(@PathVariable String id) {
    try {
      Integer parsedId = Integer.parseInt(id);
      Video video = videoService.getById(parsedId);
      return ResponseEntity.status(HttpStatus.OK).body(video);
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

  /** metodo updateVideoId. */
  @PutMapping("/{id}")
  public ResponseEntity<HashMap<String, String>> getVideoId(@PathVariable String id,
      @RequestBody Video video) {
    try {
      Integer parsedId = Integer.parseInt(id);
      videoService.update(video, parsedId);
      return ResponseEntity.status(HttpStatus.OK).body(new MensagemController().atualizar());
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }

  /** metodo postVideo. */
  @PostMapping
  public ResponseEntity<HashMap<String, String>> saveVideo(@RequestBody Video video) {
    videoService.save(video);
    return ResponseEntity.status(HttpStatus.CREATED).body(new MensagemController().mensagem());
  }

  /** metodo deleteVideo. */
  @DeleteMapping("/{id}")
  public ResponseEntity<HashMap<String, String>> deleteVideo(@PathVariable String id) {
    try {
      Integer parsedId = Integer.parseInt(id);
      videoService.delete(parsedId);
      return ResponseEntity.status(HttpStatus.OK).body(new MensagemController().excluir());
    } catch (NumberFormatException e) {
      throw new InternalException();
    }
  }
}
