package eu.przemyslawrutkowski.pokolei.repository;

import eu.przemyslawrutkowski.pokolei.entity.Car;
import eu.przemyslawrutkowski.pokolei.entity.Train;
import eu.przemyslawrutkowski.pokolei.entity.TrainCarOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrainCarOrderRepository extends JpaRepository<TrainCarOrder, Long> {
    @Query("SELECT c, tco.carOrder, tco.carNumber, tco.carAdditionalInfo " +
            "FROM TrainCarOrder tco JOIN Car c ON tco.car.carId = c.carId " +
            "WHERE tco.train.trainId = :trainId ORDER BY tco.carOrder")
    List<Object[]> findCarsByTrainId(@Param("trainId") Long trainId);

    TrainCarOrder findByTrainAndCar(Train train, Car car);

    List<TrainCarOrder> findByTrain(Train train);
}
