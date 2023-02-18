package eu.przemyslawrutkowski.pokolei.repository;

import eu.przemyslawrutkowski.pokolei.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {

    @Query("SELECT t FROM Train t WHERE t.trainNumber ILIKE %:searchTerm% OR t.trainName ILIKE %:searchTerm%")
    List<Train> findByNumberOrName(@Param("searchTerm") String searchTerm);
}
