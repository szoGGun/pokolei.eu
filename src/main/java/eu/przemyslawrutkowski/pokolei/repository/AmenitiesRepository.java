package eu.przemyslawrutkowski.pokolei.repository;

import eu.przemyslawrutkowski.pokolei.entity.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenitiesRepository extends JpaRepository<Amenities, Long> {
    Amenities findAmenitiesByAmenityId(Long id);
}
