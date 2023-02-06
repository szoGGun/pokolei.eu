package eu.przemyslawrutkowski.pokolei.controller;

import eu.przemyslawrutkowski.pokolei.dto.*;
import eu.przemyslawrutkowski.pokolei.entity.*;
import eu.przemyslawrutkowski.pokolei.repository.*;
import eu.przemyslawrutkowski.pokolei.service.CarService;
import eu.przemyslawrutkowski.pokolei.service.LocomotiveService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final AmenitiesRepository amenitiesRepository;

    private final CarService carService;

    private final LocomotiveService locomotiveService;

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

    @PostMapping("/trains/add")
    public ResponseEntity<Train> addTrain(@RequestBody TrainDto trainDto) {
        Train train = new Train();
        train.setTrainNumber(trainDto.getTrainNumber());
        train.setTrainName(trainDto.getTrainName());
        train.setRoute(trainDto.getRoute());
        train.setRunningDates(trainDto.getRunningDates());
        train.setAdditionalInfo(trainDto.getAdditionalInfo());

        List<TrainCarOrder> trainCarOrders = new ArrayList<>();
        CarDto[] selectedCars = trainDto.getSelectedCars();
        for (int i = 0; i < selectedCars.length; i++) {
            Car car = carService.getCarById(selectedCars[i].getCarId());
            TrainCarOrder trainCarOrder = new TrainCarOrder();
            trainCarOrder.setCar(car);
            trainCarOrder.setCarNumber(selectedCars[i].getCarNumber());
            trainCarOrder.setCarAdditionalInfo(selectedCars[i].getAdditionalInfo());
            trainCarOrder.setCarOrder(i + 1);
            trainCarOrder.setTrain(train);
            trainCarOrders.add(trainCarOrder);
        }
        train.setTrainCarOrders(trainCarOrders);

        List<TrainLocomotiveOrder> trainLocomotiveOrders = new ArrayList<>();
        LocomotiveDto[] selectedLocomotives = trainDto.getSelectedLocomotives();
        for (int i = 0; i < selectedLocomotives.length; i++) {
            Locomotive locomotive = locomotiveService.getLocomotiveById(selectedLocomotives[i].getLocomotiveId());
            TrainLocomotiveOrder trainLocomotiveOrder = new TrainLocomotiveOrder();
            trainLocomotiveOrder.setLocomotive(locomotive);
            trainLocomotiveOrder.setLocomotiveOrder(i + 1);
            trainLocomotiveOrder.setLocomotiveAdditionalInfo(selectedLocomotives[i].getAdditionalInfo());
            trainLocomotiveOrder.setTrain(train);
            trainLocomotiveOrders.add(trainLocomotiveOrder);
        }
        train.setTrainLocomotiveOrders(trainLocomotiveOrders);

        train = trainRepository.save(train);
        return new ResponseEntity<>(train, HttpStatus.CREATED);
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
            Long amenityId = car.getAmenities().getAmenityId();
            Amenities amenities = amenitiesRepository.findAmenitiesByAmenityId(amenityId);
            cars.add(createTrainCarDto(car, order, carNumber, carAdditionalInfo, amenities));
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
            String locomotiveAdditionalInfo = (String) locomotiveAndOrder[2];
            locomotives.add(new TrainLocomotiveDto(locomotive.getLocomotiveId(), locomotive.getName(),
                    locomotive.getDrivingSpeed(), locomotive.getWeight(), locomotive.getPictureLink(), locomotiveAdditionalInfo, order));
        }
        return locomotives;
    }

    private TrainCarDto createTrainCarDto(Car car, int order, int carNumber, String carAdditionalInfo, Amenities amenities) {
        return new TrainCarDto(car.getCarId(), car.getCarType(), car.getName(), car.getNumberOfSeats(),
                car.getTravelClass(), amenities.getAirConditioning(),  amenities.getBarCar(), amenities.getBicycles(), amenities.getCompartmentless(), amenities.getDiningCar(),
                amenities.getDisabledLift(), amenities.getDisabledSeats(), amenities.getElectricalOutlets(), amenities.getSleepingCar(),
                amenities.getToilet(), amenities.getWifi(), car.getPictureLink(), car.getSchemaLink(), carNumber,
                order, carAdditionalInfo);
    }
}
