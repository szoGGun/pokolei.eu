package eu.przemyslawrutkowski.pokolei.repository;

import eu.przemyslawrutkowski.pokolei.entity.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
}
