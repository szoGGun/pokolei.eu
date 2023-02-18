package eu.przemyslawrutkowski.pokolei.service;

import eu.przemyslawrutkowski.pokolei.dto.*;
import eu.przemyslawrutkowski.pokolei.entity.*;
import eu.przemyslawrutkowski.pokolei.repository.AmenitiesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TrainService {

    private final AmenitiesRepository amenitiesRepository;

    public List<TrainCarDto> createTrainCarDtos(List<Object[]> carsAndOrders) {
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

    public List<TrainLocomotiveDto> createTrainLocomotiveDtos(List<Object[]> locomotivesAndOrders) {
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
                car.getTravelClass(), amenities.getAirConditioning(), amenities.getBarCar(), amenities.getBicycles(), amenities.getCompartmentless(), amenities.getDiningCar(),
                amenities.getDisabledLift(), amenities.getDisabledSeats(), amenities.getElectricalOutlets(), amenities.getSleepingCar(),
                amenities.getToilet(), amenities.getWifi(), car.getPictureLink(), car.getSchemaLink(), carNumber,
                order, carAdditionalInfo);
    }
}
