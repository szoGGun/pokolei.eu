create table cars
(
    car_id              serial primary key,
    number              integer      not null,
    name                varchar(30)  not null,
    number_of_seats     integer      not null,
    places_for_bicycles boolean      not null,
    dining_car          boolean      not null,
    bar_car             boolean      not null,
    travel_class        integer      not null check (travel_class in (1, 2)),
    air_conditioning    boolean      not null,
    electrical_outlets  boolean      not null,
    toilet              boolean      not null,
    picture_link        varchar(200) not null,
    schema_link         varchar(200) not null
);

insert into cars (number, name, number_of_seats, places_for_bicycles, dining_car, bar_car, travel_class,
                  air_conditioning, electrical_outlets, toilet, picture_link, schema_link)
values (1, 'Car 1', 100, true, true, false, 1, true, true, true, 'https://example.com/car1.jpg',
        'https://example.com/car1schema.jpg'),
       (2, 'Car 2', 50, false, false, true, 2, true, true, true, 'https://example.com/car2.jpg',
        'https://example.com/car2schema.jpg'),
       (3, 'Car 3', 75, true, true, true, 1, true, true, true, 'https://example.com/car3.jpg',
        'https://example.com/car3schema.jpg');
