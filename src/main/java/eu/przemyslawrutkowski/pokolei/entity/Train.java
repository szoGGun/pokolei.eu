package eu.przemyslawrutkowski.pokolei.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainId;

    @NotBlank
    private String trainNumber;

    private String trainName;

    @NotBlank
    private String route;

    @NotBlank
    private String runningDates;

    private String additionalInfo;

    @JsonBackReference("train-car")
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<TrainCarOrder> trainCarOrders;

    @JsonBackReference("train-locomotive")
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<TrainLocomotiveOrder> trainLocomotiveOrders;
}
