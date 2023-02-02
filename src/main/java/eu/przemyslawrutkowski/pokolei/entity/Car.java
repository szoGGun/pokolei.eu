package eu.przemyslawrutkowski.pokolei.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Size(max = 10)
    private Integer number;

    @NotNull
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 3)
    private Integer numberOfSeats;

    @Size(max = 1)
    private Integer travelClass;

    @NotNull
    @Size(max = 200)
    private String pictureLink;

    @Size(max = 200)
    private String schemaLink;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "amenities_id", nullable = false)
    private Amenities amenities;

    @OneToMany(mappedBy = "car")
    private List<TrainCarOrder> trainCarOrders;
}
