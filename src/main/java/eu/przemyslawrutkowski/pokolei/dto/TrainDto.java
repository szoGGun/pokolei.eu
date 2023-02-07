package eu.przemyslawrutkowski.pokolei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainDto {

    private Long trainId;
    private String trainNumber;
    private String trainName;
    private String route;
    private String runningDates;
    private String additionalInfo;
    private CarDto[] selectedCars;
    private LocomotiveDto[] selectedLocomotives;
}
