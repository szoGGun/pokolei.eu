package eu.przemyslawrutkowski.pokolei.entity;

import eu.przemyslawrutkowski.pokolei.validator.ValidTravelClass;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @NotBlank
    @Size(max = 10)
    private Integer number;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 3)
    private Integer numberOfSeats;

    @NotBlank
    @Size(max = 1)
    private Boolean placesForBicycles;

    @NotBlank
    @Size(max = 1)
    private Boolean diningCar;

    @NotBlank
    @Size(max = 1)
    private Boolean barCar;

    @ValidTravelClass
    @Size(max = 1)
    private Integer travelClass;

    @NotBlank
    @Size(max = 1)
    private Boolean airConditioning;

    @NotBlank
    @Size(max = 1)
    private Boolean electricalOutlets;

    @NotBlank
    @Size(max = 1)
    private Boolean toilet;

    @NotBlank
    @Size(max = 200)
    private String pictureLink;

    @Size(max = 200)
    private String schemaLink;

    @OneToMany(mappedBy = "car")
    private List<TrainCarOrder> trainCarOrders;
}
