package eu.przemyslawrutkowski.pokolei.controller;

import eu.przemyslawrutkowski.pokolei.dto.*;
import eu.przemyslawrutkowski.pokolei.entity.*;
import eu.przemyslawrutkowski.pokolei.exception.ResourceNotFoundException;
import eu.przemyslawrutkowski.pokolei.repository.*;
import eu.przemyslawrutkowski.pokolei.service.CarService;
import eu.przemyslawrutkowski.pokolei.service.LocomotiveService;
import eu.przemyslawrutkowski.pokolei.service.TrainService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TrainController {

    private final TrainRepository trainRepository;

    private final TrainCarOrderRepository trainCarOrderRepository;

    private final TrainLocomotiveOrderRepository trainLocomotiveOrderRepository;

    private final CarService carService;

    private final TrainService trainService;

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

    @GetMapping("/{trainId}/cars")
    public List<TrainCarDto> getCarsByTrainId(@PathVariable Long trainId) {
        List<Object[]> carsAndOrders = trainCarOrderRepository.findCarsByTrainId(trainId);
        return trainService.createTrainCarDtos(carsAndOrders);
    }

    @GetMapping("/{trainId}/locomotive")
    public List<TrainLocomotiveDto> getLocomotiveByTrainId(@PathVariable Long trainId) {
        List<Object[]> locomotivesAndOrders = trainLocomotiveOrderRepository.findLocomotiveByTrainId(trainId);
        return trainService.createTrainLocomotiveDtos(locomotivesAndOrders);
    }

    @GetMapping("/trains/get/{trainId}")
    public ResponseEntity<TrainDto> getTrain(@PathVariable Long trainId) {
        Train train = trainRepository.findById(trainId).orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + trainId));
        TrainDto trainDto = new TrainDto();
        trainDto.setTrainId(train.getTrainId());
        trainDto.setTrainNumber(train.getTrainNumber());
        trainDto.setTrainName(train.getTrainName());
        trainDto.setRoute(train.getRoute());
        trainDto.setRunningDates(train.getRunningDates());
        trainDto.setAdditionalInfo(train.getAdditionalInfo());

        List<TrainCarOrder> trainCarOrders = train.getTrainCarOrders();
        CarDto[] selectedCars = new CarDto[trainCarOrders.size()];
        for (int i = 0; i < trainCarOrders.size(); i++) {
            CarDto carDto = new CarDto();
            carDto.setAdditionalInfo(trainCarOrders.get(i).getCarAdditionalInfo());
            carDto.setCarId(trainCarOrders.get(i).getCar().getCarId());
            carDto.setCarNumber(trainCarOrders.get(i).getCarNumber());
            carDto.setCarType(trainCarOrders.get(i).getCar().getCarType());
            carDto.setName(trainCarOrders.get(i).getCar().getName());
            carDto.setNumberOfSeats(trainCarOrders.get(i).getCar().getNumberOfSeats());
            carDto.setPictureLink(trainCarOrders.get(i).getCar().getPictureLink());
            carDto.setSchemaLink(trainCarOrders.get(i).getCar().getSchemaLink());
            carDto.setTravelClass(trainCarOrders.get(i).getCar().getTravelClass());
            carDto.setOrder(trainCarOrders.get(i).getCarOrder());
            selectedCars[i] = carDto;
        }
        Arrays.sort(selectedCars, Comparator.comparingInt(CarDto::getOrder));
        trainDto.setSelectedCars(selectedCars);

        List<TrainLocomotiveOrder> trainLocomotiveOrders = train.getTrainLocomotiveOrders();
        LocomotiveDto[] selectedLocomotives = new LocomotiveDto[trainLocomotiveOrders.size()];
        for (int i = 0; i < trainLocomotiveOrders.size(); i++) {
            LocomotiveDto locomotiveDto = new LocomotiveDto();
            locomotiveDto.setAdditionalInfo(trainLocomotiveOrders.get(i).getLocomotiveAdditionalInfo());
            locomotiveDto.setDrivingSpeed(trainLocomotiveOrders.get(i).getLocomotive().getDrivingSpeed());
            locomotiveDto.setLocomotiveId(trainLocomotiveOrders.get(i).getLocomotive().getLocomotiveId());
            locomotiveDto.setName(trainLocomotiveOrders.get(i).getLocomotive().getName());
            locomotiveDto.setPictureLink(trainLocomotiveOrders.get(i).getLocomotive().getPictureLink());
            locomotiveDto.setWeight(trainLocomotiveOrders.get(i).getLocomotive().getWeight());
            locomotiveDto.setOrder(trainLocomotiveOrders.get(i).getLocomotiveOrder());
            selectedLocomotives[i] = locomotiveDto;
        }
        Arrays.sort(selectedLocomotives, Comparator.comparingInt(LocomotiveDto::getOrder));
        trainDto.setSelectedLocomotives(selectedLocomotives);

        return new ResponseEntity<>(trainDto, HttpStatus.OK);
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

    @PutMapping("/trains/edit")
    public ResponseEntity<TrainDto> editTrain(@RequestBody TrainDto trainDto) {
        Train train = trainRepository.findById(trainDto.getTrainId())
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + trainDto.getTrainId()));

        train.setTrainNumber(trainDto.getTrainNumber());
        train.setTrainName(trainDto.getTrainName());
        train.setRoute(trainDto.getRoute());
        train.setRunningDates(trainDto.getRunningDates());
        train.setAdditionalInfo(trainDto.getAdditionalInfo());

        List<TrainCarOrder> currentTrainCarOrders = trainCarOrderRepository.findByTrain(train);
        trainCarOrderRepository.deleteAll(currentTrainCarOrders);

        train.getTrainCarOrders().clear();
        int carOrder = 1;
        for (CarDto carDto : trainDto.getSelectedCars()) {
            Car car = carService.getCarById(carDto.getCarId());
            TrainCarOrder trainCarOrder = trainCarOrderRepository.findByTrainAndCar(train, car);
            if (trainCarOrder == null) {
                trainCarOrder = new TrainCarOrder();
            }
            trainCarOrder.setCarAdditionalInfo(carDto.getAdditionalInfo());
            trainCarOrder.setCarNumber(carDto.getCarNumber());
            trainCarOrder.setCar(carService.getCarById(carDto.getCarId()));
            trainCarOrder.setTrain(train);
            trainCarOrder.setCarOrder(carOrder);
            train.getTrainCarOrders().add(trainCarOrder);
            carOrder++;
        }

        List<TrainLocomotiveOrder> currentTrainLocomotiveOrders = trainLocomotiveOrderRepository.findByTrain(train);
        trainLocomotiveOrderRepository.deleteAll(currentTrainLocomotiveOrders);

        train.getTrainLocomotiveOrders().clear();
        int locomotiveOrder = 1;
        for (LocomotiveDto locomotiveDto : trainDto.getSelectedLocomotives()) {
            Locomotive locomotive = locomotiveService.getLocomotiveById(locomotiveDto.getLocomotiveId());
            TrainLocomotiveOrder trainLocomotiveOrder = trainLocomotiveOrderRepository.findByTrainAndLocomotive(train, locomotive);
            if (trainLocomotiveOrder == null) {
                trainLocomotiveOrder = new TrainLocomotiveOrder();
            }
            trainLocomotiveOrder.setLocomotiveAdditionalInfo(locomotiveDto.getAdditionalInfo());
            trainLocomotiveOrder.setLocomotive(locomotiveService.getLocomotiveById(locomotiveDto.getLocomotiveId()));
            trainLocomotiveOrder.setTrain(train);
            trainLocomotiveOrder.setLocomotiveOrder(locomotiveOrder);
            train.getTrainLocomotiveOrders().add(trainLocomotiveOrder);
            locomotiveOrder++;
        }

        train = trainRepository.save(train);

        TrainDto updatedTrainDto = new TrainDto();
        updatedTrainDto.setTrainId(train.getTrainId());
        updatedTrainDto.setTrainNumber(train.getTrainNumber());
        updatedTrainDto.setTrainName(train.getTrainName());
        updatedTrainDto.setRoute(train.getRoute());
        updatedTrainDto.setRunningDates(train.getRunningDates());
        updatedTrainDto.setAdditionalInfo(train.getAdditionalInfo());
        updatedTrainDto.setSelectedCars(trainDto.getSelectedCars());
        updatedTrainDto.setSelectedLocomotives(trainDto.getSelectedLocomotives());

        return ResponseEntity.ok(updatedTrainDto);
    }

    @DeleteMapping("/trains/delete/{trainId}")
    public ResponseEntity<Void> deleteTrain(@PathVariable Long trainId) {
        Train train = trainRepository.findById(trainId)
                .orElseThrow(() -> new ResourceNotFoundException("Train not found with id: " + trainId));
        trainRepository.delete(train);
        return ResponseEntity.noContent().build();
    }
}
