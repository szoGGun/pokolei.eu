package eu.przemyslawrutkowski.pokolei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AmenitiesDto {
    private Long amenityId;
    private boolean airConditioning;
    private boolean barCar;
    private boolean bicycles;
    private boolean compartmentless;
    private boolean diningCar;
    private boolean disabledLift;
    private boolean disabledSeats;
    private boolean electricalOutlets;
    private boolean sleepingCar;
    private boolean toilet;
    private boolean wifi;
}
