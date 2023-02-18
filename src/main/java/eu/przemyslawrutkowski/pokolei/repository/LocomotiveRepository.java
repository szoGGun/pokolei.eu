package eu.przemyslawrutkowski.pokolei.repository;

import eu.przemyslawrutkowski.pokolei.entity.Locomotive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocomotiveRepository extends JpaRepository<Locomotive, Long> {
}
