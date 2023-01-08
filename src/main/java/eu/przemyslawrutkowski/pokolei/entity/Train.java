package eu.przemyslawrutkowski.pokolei.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private long id;

    private String brand;

    @Column(name = "train_number", nullable = false)
    private String trainNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String route;

    @Column(name = "running_dates", nullable = false)
    private String runningDates;

    @Column(nullable = false)
    private String carriers;
}
