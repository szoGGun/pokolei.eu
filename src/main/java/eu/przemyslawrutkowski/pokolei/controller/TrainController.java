package eu.przemyslawrutkowski.pokolei.controller;

import eu.przemyslawrutkowski.pokolei.dto.TrainCarDto;
import eu.przemyslawrutkowski.pokolei.dto.TrainLocomotiveDto;
import eu.przemyslawrutkowski.pokolei.entity.Car;
import eu.przemyslawrutkowski.pokolei.entity.Locomotive;
import eu.przemyslawrutkowski.pokolei.entity.Train;
import eu.przemyslawrutkowski.pokolei.repository.TrainCarOrderRepository;
import eu.przemyslawrutkowski.pokolei.repository.TrainLocomotiveOrderRepository;
import eu.przemyslawrutkowski.pokolei.repository.TrainRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TrainController {

    private final TrainRepository trainRepository;

    private final TrainCarOrderRepository trainCarOrderRepository;

    private final TrainLocomotiveOrderRepository trainLocomotiveOrderRepository;

    @GetMapping("/trains")
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @GetMapping("/trains/search")
    public List<Train> searchTrainsByName(@RequestParam(name = "searchTerm") String searchTerm) {

        List<Train> trains = new ArrayList<>();

        if (searchTerm != null) {
            trains.addAll(trainRepository.findByNumberOrName(searchTerm));
        }

        return trains;
    }

    @GetMapping("/{trainId}/cars")
    public List<TrainCarDto> getCarsByTrainId(@PathVariable Long trainId) {
        List<Object[]> carsAndOrders = trainCarOrderRepository.findCarsByTrainId(trainId);
        List<TrainCarDto> cars = new ArrayList<>();
        for (Object[] carAndOrder : carsAndOrders) {
            Car car = (Car) carAndOrder[0];
            int order = (int) carAndOrder[1];
            int carNumber = (int) carAndOrder[2];
            String carAdditionalInfo = (String) carAndOrder[3];
            cars.add(new TrainCarDto(car.getCarId(), car.getNumber(), car.getName(), car.getNumberOfSeats(),
                    car.getPlacesForBicycles(), car.getDiningCar(), car.getBarCar(), car.getTravelClass(),
                    car.getAirConditioning(), car.getElectricalOutlets(), car.getToilet(), car.getPictureLink(),
                    car.getSchemaLink(), carNumber, order, carAdditionalInfo));
        }
        return cars;
    }

    @GetMapping("/{trainId}/locomotive")
    public List<TrainLocomotiveDto> getLocomotiveByTrainId(@PathVariable Long trainId) {
        List<Object[]> locomotivesAndOrders = trainLocomotiveOrderRepository.findLocomotiveByTrainId(trainId);
        List<TrainLocomotiveDto> locomotives = new ArrayList<>();
        for (Object[] locomotiveAndOrder : locomotivesAndOrders) {
            Locomotive locomotive = (Locomotive) locomotiveAndOrder[0];
            int order = (int) locomotiveAndOrder[1];
            locomotives.add(new TrainLocomotiveDto(locomotive.getLocomotiveId(), locomotive.getName(),
                    locomotive.getDrivingSpeed(), locomotive.getWeight(), locomotive.getPictureLink(), order));
        }
        return locomotives;
    }
}
