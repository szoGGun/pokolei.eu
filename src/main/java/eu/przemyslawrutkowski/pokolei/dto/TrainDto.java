package eu.przemyslawrutkowski.pokolei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrainDto {
    private String trainNumber;
    private String trainName;
    private String route;
    private String runningDates;
    private String additionalInfo;
    private CarDto[] selectedCars;
    private LocomotiveDto[] selectedLocomotives;
}
