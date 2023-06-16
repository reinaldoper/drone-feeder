package com.drone.feeder.projetodronefeeder.service;

import com.drone.feeder.projetodronefeeder.exceptions.VideoNotFound;
import com.drone.feeder.projetodronefeeder.model.Video;
import com.drone.feeder.projetodronefeeder.repository.VideoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** class videoService. */
@Service
public class VideoService {

  @Autowired
  VideoRepository videoRepo;

  /** metodo getAll. */

  public List<Video> getAllVideos() {
    return videoRepo.findAll();
  }

  /** metodo save. */

  public void save(Video video) {
    videoRepo.save(video);
  }

  /** metodo update. */

  public void update(Video video, Integer id) {
    Video videoId = videoRepo.findById(id).orElse(null);
    if (videoId != null) {
      videoId.setNomeArquivo(video.getNomeArquivo());
      videoRepo.save(videoId);
    } else {
      throw new VideoNotFound();
    }
  }

  /** delete video. */
  public void delete(Integer id) {
    Video videoId = videoRepo.findById(id).orElse(null);
    if (videoId != null) {
      videoRepo.delete(videoId);
    } else {
      throw new VideoNotFound();
    }
  }

  /** get videoById. */
  public Video getById(Integer id) {
    Video videoId = videoRepo.findById(id).orElse(null);
    if (videoId != null) {
      return videoId;
    } else {
      throw new VideoNotFound();
    }
  }
}
