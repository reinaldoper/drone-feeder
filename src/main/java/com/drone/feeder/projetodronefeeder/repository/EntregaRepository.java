package com.drone.feeder.projetodronefeeder.repository;

import com.drone.feeder.projetodronefeeder.model.Drone;
import com.drone.feeder.projetodronefeeder.model.Entrega;
import com.drone.feeder.projetodronefeeder.model.Video;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositório Entrega
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

  /** verifica se existe video. */
  public boolean existsByVideo(Video video);

  /** verifica se existe drone. */
  List<Entrega> findByDrone(Drone drone);
}
