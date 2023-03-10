CREATE TABLE train_locomotive_order
(
    train_locomotive_order_id SERIAL PRIMARY KEY,
    train_id                  BIGINT NOT NULL,
    locomotive_id             BIGINT NOT NULL,
    locomotive_order           INT    NOT NULL,
    locomotive_additional_info VARCHAR(1000),
    FOREIGN KEY (train_id) REFERENCES trains (train_id),
    FOREIGN KEY (locomotive_id) REFERENCES locomotives (locomotive_id)
);
INSERT INTO train_locomotive_order (train_id, locomotive_id, locomotive_order, locomotive_additional_info)
VALUES (1, 3, 1, 'Bohumin - Katowice'),
       (2, 1, 1, 'Gdynia Gł - Katowice'),
       (2, 2, 2, 'Katowice - Bohumin'),
       (3, 1, 1, 'Warszawa Wsch. - Katowice'),
       (3, 1, 2, 'Katowice - Bohumin');
