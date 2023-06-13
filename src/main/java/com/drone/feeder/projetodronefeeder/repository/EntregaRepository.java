package com.drone.feeder.projetodronefeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.drone.feeder.projetodronefeeder.model.Entrega;

// Repositório Entrega
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

}
