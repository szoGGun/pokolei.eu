CREATE TABLE train_car_order
(
    train_car_order_id  SERIAL PRIMARY KEY,
    train_id            BIGINT NOT NULL,
    car_id              BIGINT NOT NULL,
    car_number          INT    NOT NULL,
    car_order           INT    NOT NULL,
    car_additional_info VARCHAR(1000),
    FOREIGN KEY (train_id) REFERENCES trains (train_id),
    FOREIGN KEY (car_id) REFERENCES cars (car_id)
);

INSERT INTO train_car_order (train_id, car_id, car_number, car_order, car_additional_info)
VALUES (1, 1, 348, 1, null),
       (1, 2, 349, 2, null),
       (1, 3, 350, 3, 'przedział Nr. 1 dla osób niepełnosprawnych na wózkach inwalidzkich, Nr. 2 dla mini-bar, Nr. 3 dla podróżnych niepełnosprawnych, Nr. 4 dla podróżnych z dziećmi, Nr. 10 dla drużyny konduktorskiej'),
       (1, 4, 351, 4, null),
       (1, 5, 352, 5, null),
       (2, 6, 355, 1, 'Gdynia Gł. - Bohumín'),
       (2, 6, 354, 2, 'Gdynia Gł. - Bohumín'),
       (2, 6, 353, 3, 'Gdynia Gł. - Bohumín'),
       (2, 5, 352, 4, 'Gdynia Gł. - Wien Hbf'),
       (2, 4, 351, 5, 'Gdynia Gł. - Wien Hbf'),
       (2, 3, 350, 6, 'Gdynia Gł. - Wien Hbf'),
       (2, 2, 349, 7, 'Gdynia Gł. - Wien Hbf'),
       (2, 1, 348, 8, 'Gdynia Gł. - Wien Hbf'),
       (3, 7, 347, 1, 'miejsca 11-16 dla drużyny konduktorskiej; miejsca 111, 113, 115 dla niepełnosprawnych;'),
       (3, 8, 348, 2, 'miejsca 92, 94, 96, 98, 102, 104, 106, 108 dla niepełnosprawnych; miejsca Nr. 11, 12, 15, 16, 18 dla osób przewożących rowery'),
       (3, 8, 349, 3, 'miejsca 92, 94, 96, 98, 102, 104, 106, 108 dla niepełnosprawnych; miejsca Nr. 11, 12, 15, 16, 18 dla osób przewożących rowery'),
       (3, 9, 350, 4, 'przedział Nr. 1 dla podróżnych niepełnosprawnych, Nr. 5 i 10 dla osób przewożących rowery, Nr. 6-8 dla podróżnych z dziećmi, Nr. 9 dla kobiety'),
       (3, 10, 351, 5, 'miejsca 13, 15 dla niepełnosprawnych; miejsca 51-66 poza systemem rezerwacji;'),
       (3, 5, 352, 6, 'miejsca 11-16 dla niepełnosprawnych; kursuje w ⑤, ⑥, ⑦ oraz 26.XII, 10.IV, 01, 02, 03.V, 07, 08.VI, 14, 15.VIII, 31.X, 01.XI oprócz 24.XII, 09.IV;'),
       (3, 6, 353, 7, 'kursuje w ⑤, ⑥, ⑦ oraz 26.XII, 10.IV, 01, 02, 03.V, 07, 08.VI, 14, 15.VIII, 31.X, 01.XI oprócz 24.XII, 09.IV; '),
       (3, 6, 354, 8, 'kursuje w ⑤, ⑥, ⑦ oraz 26.XII, 10.IV, 01, 02, 03.V, 07, 08.VI, 14, 15.VIII, 31.X, 01.XI oprócz 24.XII, 09.IV; ');
