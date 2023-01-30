create table locomotives
(
    locomotive_id SERIAL PRIMARY KEY,
    name          VARCHAR(30)  NOT NULL,
    driving_speed VARCHAR(10)  NOT NULL,
    weight        VARCHAR(10)  NOT NULL,
    picture_link  varchar(200) NOT NULL
);

INSERT INTO locomotives (name, driving_speed, weight, picture_link)
VALUES ('EP09', '160 km/h', '455 t', 'Bmnopuvz-159A-a.gif'),
       ('EP09', '130 km/h', '455 t', 'Bmnopuvz-159A-a.gif'),
       ('EU07', '125 km/h', '275 t', 'EU07-IC-a.gif');


