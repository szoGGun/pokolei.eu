package eu.przemyslawrutkowski.pokolei.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "train_car_order")
public class TrainCarOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_car_order_id")
    private long trainCarOrderId;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "car_order", nullable = false)
    private int carOrder;
}
