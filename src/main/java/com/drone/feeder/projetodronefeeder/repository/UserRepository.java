package com.drone.feeder.projetodronefeeder.repository;

import com.drone.feeder.projetodronefeeder.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * classe repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);
}
