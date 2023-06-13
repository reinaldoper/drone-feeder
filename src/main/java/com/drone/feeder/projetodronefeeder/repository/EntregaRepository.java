package com.drone.feeder.projetodronefeeder.repository;

import com.drone.feeder.projetodronefeeder.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositório Entrega
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {

}
