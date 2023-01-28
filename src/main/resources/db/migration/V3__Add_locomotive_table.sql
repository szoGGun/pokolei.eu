create table locomotives
(
    locomotive_id SERIAL PRIMARY KEY,
    name          VARCHAR(30) NOT NULL,
    driving_speed VARCHAR(10) NOT NULL,
    weight        VARCHAR(10) NOT NULL
);

INSERT INTO locomotives (name, driving_speed, weight)
VALUES ('EP09', '160 km/h', '455 t'),
       ('EP09', '130 km/h', '455 t'),
       ('EU07', '125 km/h', '215 t');


