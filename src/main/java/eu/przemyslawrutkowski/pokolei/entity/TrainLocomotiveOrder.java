package eu.przemyslawrutkowski.pokolei.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "train_locomotive_order")
public class TrainLocomotiveOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_locomotive_order_id")
    private long trainLocomotiveOrderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @ManyToOne
    @JoinColumn(name = "locomotive_id", nullable = false)
    private Locomotive locomotive;

    @Column(name = "locomotive_order", nullable = false)
    private int locomotiveOrder;

    @Column(name = "locomotive_additional_info")
    private String locomotiveAdditionalInfo;
}
