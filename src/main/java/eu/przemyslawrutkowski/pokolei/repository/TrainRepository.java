package eu.przemyslawrutkowski.pokolei.repository;

import eu.przemyslawrutkowski.pokolei.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Long> {
}
