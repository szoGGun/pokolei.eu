package eu.przemyslawrutkowski.pokolei.entity;

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
@Table(name = "locomotives")
public class Locomotive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locomotiveId;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 10)
    private String drivingSpeed;

    @NotBlank
    @Size(max = 10)
    private String weight;

    @NotBlank
    @Size(max = 200)
    private String pictureLink;

    @OneToMany(mappedBy = "locomotive")
    private List<TrainLocomotiveOrder> trainLocomotiveOrders;
}
