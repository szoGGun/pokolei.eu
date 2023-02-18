CREATE TABLE trains
(
    train_id        SERIAL PRIMARY KEY,
    train_number    VARCHAR(30)  NOT NULL,
    train_name      VARCHAR(30),
    route           VARCHAR(500) NOT NULL,
    running_dates   VARCHAR(30)  NOT NULL,
    additional_info VARCHAR(1000)
);
INSERT INTO trains (train_number, train_name, route, running_dates, additional_info)
VALUES ('(100) 44000', 'IC Moravia', 'Wien Hbf 18:10 - Břeclav - Přerov - Ostrava hl.n. - Bohumin (21:15/21:27) - Chałupki - Racibórz - Gliwice - Katowice 23:02', 'Codziennie', 'Zmiana kierunku lub lokomotywy na stacjach: Wien Hbf'),
       ('(107) 54000/1', 'IC Sobieski', 'Gdynia Gł. 10:54 - Gdańsk Gł. - Tczew - Działdowo - Warszawa Centr. - Zawiercie - Sosonowiec Gł. - Katowice - Tychy - Rybnik - Chałupki - Bohumin (18:30/18:42) - Ostrava hl.n. - Přerov - Břeclav - Wien Hbf 21:49', 'Codziennie', 'Zmiana kierunku lub lokomotywy na stacjach: Bohumin'),
       ('(108) 14008/9', 'IC Silesia', 'Warszawa Wsch. 17:34 - Warszawa Centr. - Włoszczowa Płn. - Katowice - Tychy - Wodzisław Śl. - Chałupki - Bohumin 21:51', 'codziennie od 01.IX do 30.X', '');
