package eu.przemyslawrutkowski.pokolei.controller;

import eu.przemyslawrutkowski.pokolei.entity.Amenities;
import eu.przemyslawrutkowski.pokolei.entity.Car;
import eu.przemyslawrutkowski.pokolei.exception.ResourceNotFoundException;
import eu.przemyslawrutkowski.pokolei.repository.CarRepository;
import eu.przemyslawrutkowski.pokolei.repository.TrainCarOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CarController {

    private final CarRepository carRepository;
    private final TrainCarOrderRepository trainCarOrderRepository;

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/cars/get/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + carId));
        return ResponseEntity.ok().body(car);
    }

    @PutMapping("/cars/update/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id,
                                         @RequestBody Car editCarData) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));

        car.setCarType(editCarData.getCarType());
        car.setName(editCarData.getName());
        car.setNumberOfSeats(editCarData.getNumberOfSeats());
        car.setTravelClass(editCarData.getTravelClass());


        Amenities amenities = car.getAmenities();
        amenities.setAirConditioning(editCarData.getAmenities().getAirConditioning());
        amenities.setBarCar(editCarData.getAmenities().getBarCar());
        amenities.setBicycles(editCarData.getAmenities().getBicycles());
        amenities.setCompartmentless(editCarData.getAmenities().getCompartmentless());
        amenities.setDiningCar(editCarData.getAmenities().getDiningCar());
        amenities.setDisabledLift(editCarData.getAmenities().getDisabledLift());
        amenities.setDisabledSeats(editCarData.getAmenities().getDisabledSeats());
        amenities.setElectricalOutlets(editCarData.getAmenities().getElectricalOutlets());
        amenities.setSleepingCar(editCarData.getAmenities().getSleepingCar());
        amenities.setToilet(editCarData.getAmenities().getToilet());
        amenities.setWifi(editCarData.getAmenities().getWifi());

        car.setAmenities(amenities);

        Car updatedCar = carRepository.save(car);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/cars/delete/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with id: " + id));

        trainCarOrderRepository.deleteAllInBatch(car.getTrainCarOrders());
        carRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
