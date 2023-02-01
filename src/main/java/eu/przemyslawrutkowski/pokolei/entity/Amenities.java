package eu.przemyslawrutkowski.pokolei.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "amenities")
public class Amenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amenityId;

    @NotBlank
    @Size(max = 1)
    private Boolean airConditioning;

    @NotBlank
    @Size(max = 1)
    private Boolean barCar;

    @NotBlank
    @Size(max = 1)
    private Boolean bicycles;

    @NotBlank
    @Size(max = 1)
    private Boolean compartmentless;

    @NotBlank
    @Size(max = 1)
    private Boolean diningCar;

    @NotBlank
    @Size(max = 1)
    private Boolean disabledLift;

    @NotBlank
    @Size(max = 1)
    private Boolean disabledSeats;

    @NotBlank
    @Size(max = 1)
    private Boolean electricalOutlets;

    @NotBlank
    @Size(max = 1)
    private Boolean sleepingCar;

    @NotBlank
    @Size(max = 1)
    private Boolean toilet;

    @NotBlank
    @Size(max = 1)
    private Boolean wifi;

    @OneToOne(mappedBy = "amenities", cascade = CascadeType.ALL)
    private Car car;
}
