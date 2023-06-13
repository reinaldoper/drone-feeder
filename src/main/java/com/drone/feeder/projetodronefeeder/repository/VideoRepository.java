package com.drone.feeder.projetodronefeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.drone.feeder.projetodronefeeder.model.Video;

// Repositório Video
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}
