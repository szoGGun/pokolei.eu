CREATE TABLE trains
(
    train_id        SERIAL PRIMARY KEY,
    train_number    VARCHAR(30)  NOT NULL,
    train_name      VARCHAR(30),
    route           VARCHAR(200) NOT NULL,
    running_dates   VARCHAR(30)  NOT NULL,
    additional_info VARCHAR(200)
);
INSERT INTO trains (train_number, train_name, route, running_dates, additional_info)
VALUES ('TRAIN1', 'Train Name 1', 'Route 1', 'Monday, Tuesday, Wednesday', 'Additional information for train 1'),
       ('TRAIN2', 'Train Name 2', 'Route 2', 'Everyday', 'Additional information for train 2'),
       ('TRAIN3', 'Train Name 2', 'Route 2', 'Monday', 'Additional information for train 3');
