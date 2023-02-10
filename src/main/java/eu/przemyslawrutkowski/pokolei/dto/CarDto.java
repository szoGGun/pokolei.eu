package eu.przemyslawrutkowski.pokolei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private Long carId;
    private String carType;
    private String name;
    private Integer numberOfSeats;
    private Integer travelClass;
    private String pictureLink;
    private String schemaLink;
    private AmenitiesDto amenities;
    private int CarNumber;
    private String additionalInfo;
    private int order;
}
