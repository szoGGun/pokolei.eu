CREATE TABLE cars
(
    car_id          serial PRIMARY KEY,
    number          integer      NOT NULL,
    name            varchar(30)  NOT NULL,
    number_of_seats integer,
    travel_class    integer CHECK (travel_class IN (1, 2, 3)),
    picture_link    varchar(200) NOT NULL,
    schema_link     varchar(200),
    amenities_id    integer      NOT NULL REFERENCES amenities (amenity_id)
);

INSERT INTO cars (number, name, number_of_seats, travel_class, picture_link, schema_link, amenities_id)
VALUES (348, 'B9mnopuvz', 72, 2, 'Bmnopuvz-159A-a.gif', '159A_B9mnopuvz.png', 1),
       (349, 'B9mnopuz', 72, 2, 'Bmnopuz-159A-a.gif', '159A_B9mnopuz.png', 2),
       (350, 'B10bmnouz', 56, 2, 'B10bmnouz-XBNMg2-FPS-a.gif', '156A_B10bmnouz.png', 3),
       (351, 'WRmnouz', NULL, NULL, 'WRmnouz-406A-30-a.gif', '', 4),
       (352, 'A9mnouz', 54, 1, 'A9mnouz-Z2AMg-FPS-a.gif', 'Z_A9mnouz.png', 5);
