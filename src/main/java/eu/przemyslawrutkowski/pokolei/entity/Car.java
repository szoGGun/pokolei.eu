package eu.przemyslawrutkowski.pokolei.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

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
    private Integer placesForBicycles;

    @NotBlank
    @Size(max = 1)
    private String diningCar;

    @NotBlank
    @Size(max = 1)
    private Integer barCar;

    @NotBlank
    @Size(max = 1)
    private Integer travelClass;

    @NotBlank
    @Size(max = 1)
    private Integer airConditioning;

    @NotBlank
    @Size(max = 1)
    private Integer electricalOutlets;

    @NotBlank
    @Size(max = 1)
    private Integer toilet;

    @NotBlank
    @Size(max = 200)
    private String pictureLink;

    @NotBlank
    @Size(max = 200)
    private String schemaLink;

    @ManyToMany(mappedBy = "cars")
    private Set<Train> trains = new HashSet<>();
}
