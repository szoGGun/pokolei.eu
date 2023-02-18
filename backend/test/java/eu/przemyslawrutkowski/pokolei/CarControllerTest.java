package eu.przemyslawrutkowski.pokolei;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import eu.przemyslawrutkowski.pokolei.controller.CarController;
import eu.przemyslawrutkowski.pokolei.entity.Amenities;
import eu.przemyslawrutkowski.pokolei.entity.Car;
import eu.przemyslawrutkowski.pokolei.repository.CarRepository;
import eu.przemyslawrutkowski.pokolei.repository.TrainCarOrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class CarControllerTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private TrainCarOrderRepository trainCarOrderRepository;

    @InjectMocks
    private CarController carController;

    private Car car;
    private List<Car> cars;

    @Before
    public void setUp() {
        car = new Car();
        car.setCarId(1L);
        car.setName("Test Car");
        car.setNumberOfSeats(10);
        car.setTravelClass(1);
        car.setAmenities(new Amenities());

        cars = new ArrayList<>();
        cars.add(car);
    }

    @Test
    public void testGetAllCars() {
        when(carRepository.findAll()).thenReturn(cars);

        List<Car> result = carController.getAllCars();

        assertEquals(cars, result);
    }

    @Test
    public void testGetCarById() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        ResponseEntity<Car> result = carController.getCarById(1L);

        assertEquals(ResponseEntity.ok().body(car), result);
    }

    @Test
    public void testUpdateCar() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carRepository.save(car)).thenReturn(car);

        ResponseEntity<Car> result = carController.updateCar(1L, car);

        assertEquals(ResponseEntity.ok(car), result);
    }

    @Test
    public void testDeleteCar() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        ResponseEntity<Void> result = carController.deleteCar(1L);

        verify(trainCarOrderRepository).deleteAllInBatch(car.getTrainCarOrders());
        verify(carRepository).deleteById(1L);
        assertEquals(ResponseEntity.ok().build(), result);
    }
}
