package eu.przemyslawrutkowski.pokolei.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "train_car_order")
public class TrainCarOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_car_order_id")
    private long trainCarOrderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "car_number", nullable = false)
    private int carNumber;

    @Column(name = "car_order", nullable = false)
    private int carOrder;

    @Column(name = "car_additional_info")
    private String carAdditionalInfo;
}
