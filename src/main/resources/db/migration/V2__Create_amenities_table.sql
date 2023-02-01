create table amenities
(
    amenity_id serial primary key,
    air_conditioning boolean not null,
    bar_car boolean not null,
    bicycles boolean not null,
    compartmentless boolean not null,
    dining_car boolean not null,
    disabled_lift boolean not null,
    disabled_seats boolean not null,
    electrical_outlets boolean not null,
    sleeping_car boolean not null,
    toilet boolean not null,
    wifi boolean not null
);

insert into amenities (air_conditioning, bar_car, bicycles, compartmentless, dining_car, disabled_lift, disabled_seats, electrical_outlets, sleeping_car, toilet, wifi)
values (true, false, true, true, false, false, false, true, false, true, false),
       (true, false, false, true, false, false, false, true, false, true, false),
       (true, false, false, false, false, false, true, true, false, true, false),
       (true, false, false, false, true, false, false, true, false, true, false),
       (true, false, false, false, false, false, false, true, false, true, false);
