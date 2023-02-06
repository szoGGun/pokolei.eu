package eu.przemyslawrutkowski.pokolei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainCarDto {
    private Long carId;
    private String carType;
    private String name;
    private Integer numberOfSeats;
    private Integer travelClass;
    private Boolean airConditioning;
    private Boolean barCar;
    private Boolean bicycles;
    private Boolean compartmentless;
    private Boolean diningCar;
    private Boolean disabledLift;
    private Boolean disabledSeats;
    private Boolean electricalOutlets;
    private Boolean sleepingCar;
    private Boolean toilet;
    private Boolean wifi;
    private String pictureLink;
    private String schemaLink;
    private int carNumber;
    private int order;
    private String additionalInfo;
}
