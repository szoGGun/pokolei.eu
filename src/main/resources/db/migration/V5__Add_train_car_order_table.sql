CREATE TABLE train_car_order
(
    train_car_order_id SERIAL PRIMARY KEY,
    train_id           BIGINT NOT NULL,
    car_id             BIGINT NOT NULL,
    car_order          INT    NOT NULL,
    FOREIGN KEY (train_id) REFERENCES trains (train_id),
    FOREIGN KEY (car_id) REFERENCES cars (car_id)
);

INSERT INTO train_car_order (train_id, car_id, car_order)
VALUES (1, 1, 1),
       (1, 2, 3),
       (1, 3, 2),
       (2, 1, 3),
       (2, 2, 2),
       (2, 3, 1),
       (3, 1, 1),
       (3, 2, 2),
       (3, 3, 3);
