create table cars
(
    car_id              serial primary key,
    number              integer      not null,
    name                varchar(30)  not null,
    number_of_seats     integer,
    places_for_bicycles boolean      not null,
    dining_car          boolean      not null,
    bar_car             boolean      not null,
    travel_class        integer check (travel_class in (1, 2)),
    air_conditioning    boolean      not null,
    electrical_outlets  boolean      not null,
    toilet              boolean      not null,
    picture_link        varchar(200) not null,
    schema_link         varchar(200)
);

insert into cars (number, name, number_of_seats, places_for_bicycles, dining_car, bar_car, travel_class,
                  air_conditioning, electrical_outlets, toilet, picture_link, schema_link)
values (348, 'Bmnopuvz', 72, true, false, false, 2, true, true, true, 'Bmnopuvz-159A-a.gif',
        '159A_B9mnopuvz.png'),
       (349, 'Bmnopuz', 72, false, false, false, 2, true, true, true, 'Bmnopuz-159A-a.gif',
        '159A_B9mnopuz.png'),
       (350, 'Bbmnouz', 56, false, false, false, 2, true, true, true, 'B10bmnouz-XBNMg2-FPS-a.gif',
        '156A_B10bmnouz.png'),
       (351, 'WRmnouz', 0, false, true, false, null, true, true, true, 'WRmnouz-406A-30-a.gif',
        ''),
       (352, 'Amnouz', 54, false, false, false, 1, true, true, true, 'A9mnouz-Z2AMg-FPS-a.gif',
        'Z_A9mnouz.png');
