package com.drone.feeder.projetodronefeeder.repository;

import com.drone.feeder.projetodronefeeder.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositório Video
@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

}
