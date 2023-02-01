CREATE TABLE train_car_order
(
    train_car_order_id SERIAL PRIMARY KEY,
    train_id           BIGINT NOT NULL,
    car_id             BIGINT NOT NULL,
    car_number         INT    NOT NULL,
    car_order          INT    NOT NULL,
    car_additional_info VARCHAR,
    FOREIGN KEY (train_id) REFERENCES trains (train_id),
    FOREIGN KEY (car_id) REFERENCES cars (car_id)
);

INSERT INTO train_car_order (train_id, car_id, car_number, car_order, car_additional_info)
VALUES (1, 1, 348, 1, null),
       (1, 2, 349, 2, null),
       (1, 3, 350, 3, 'przedział Nr. 1 dla osób niepełnosprawnych na wózkach inwalidzkich, Nr. 2 dla mini-bar, Nr. 3 dla podróżnych niepełnosprawnych, Nr. 4 dla podróżnych z dziećmi, Nr. 10 dla drużyny konduktorskiej'),
       (1, 4, 351, 4, null),
       (1, 5, 352, 5, null)
