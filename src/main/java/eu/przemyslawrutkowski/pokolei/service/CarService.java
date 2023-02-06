package eu.przemyslawrutkowski.pokolei.service;

import eu.przemyslawrutkowski.pokolei.entity.Car;
import eu.przemyslawrutkowski.pokolei.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car getCarById(long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car with id " + id + " not found"));
    }
}
