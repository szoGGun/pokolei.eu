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
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trainId;

    @NotBlank
    @Size(max = 30)
    private String trainNumber;

    @Size(max = 30)
    private String trainName;

    @NotBlank
    @Size(max = 200)
    private String route;

    @NotBlank
    @Size(max = 20)
    private String runningDates;

    @Size(max = 200)
    private String additionalInfo;

    @ManyToMany
    @JoinTable(name = "train_car",
            joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private Set<Car> cars = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "train_locomotive",
            joinColumns = @JoinColumn(name = "train_id"),
            inverseJoinColumns = @JoinColumn(name = "locomotive_id"))
    private Set<Locomotive> locomotives = new HashSet<>();
}
