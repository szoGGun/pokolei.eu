package eu.przemyslawrutkowski.pokolei.repository;

import eu.przemyslawrutkowski.pokolei.entity.Locomotive;
import eu.przemyslawrutkowski.pokolei.entity.Train;
import eu.przemyslawrutkowski.pokolei.entity.TrainLocomotiveOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainLocomotiveOrderRepository extends JpaRepository<TrainLocomotiveOrder, Long> {

    @Query("SELECT l, tlo.locomotiveOrder, tlo.locomotiveAdditionalInfo FROM TrainLocomotiveOrder tlo JOIN Locomotive l ON tlo.locomotive.locomotiveId = l.locomotiveId WHERE tlo.train.trainId = :trainId ORDER BY tlo.locomotiveOrder")
    List<Object[]> findLocomotiveByTrainId(@Param("trainId") Long trainId);

    TrainLocomotiveOrder findByTrainAndLocomotive(Train train, Locomotive locomotive);

    List<TrainLocomotiveOrder> findByTrain(Train train);
}
