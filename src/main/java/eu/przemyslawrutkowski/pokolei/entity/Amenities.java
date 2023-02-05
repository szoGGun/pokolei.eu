package eu.przemyslawrutkowski.pokolei.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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

    @JsonBackReference
    @OneToOne(mappedBy = "amenities", cascade = CascadeType.ALL)
    private Car car;
}
