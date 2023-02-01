package eu.przemyslawrutkowski.pokolei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrainCarDto {
    private Long carId;
    private Integer number;
    private String name;
    private Integer numberOfSeats;
    private Boolean placesForBicycles;
    private Boolean diningCar;
    private Boolean barCar;
    private Integer travelClass;
    private Boolean airConditioning;
    private Boolean electricalOutlets;
    private Boolean toilet;
    private String pictureLink;
    private String schemaLink;
    private int carNumber;
    private int order;

    private String additionalInfo;
}
