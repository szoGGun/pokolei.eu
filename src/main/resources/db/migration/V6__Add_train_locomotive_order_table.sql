CREATE TABLE train_locomotive_order
(
    train_locomotive_order_id SERIAL PRIMARY KEY,
    train_id                  BIGINT NOT NULL,
    locomotive_id             BIGINT NOT NULL,
    locomotive_order           INT    NOT NULL,
    FOREIGN KEY (train_id) REFERENCES trains (train_id),
    FOREIGN KEY (locomotive_id) REFERENCES locomotives (locomotive_id)
);
INSERT INTO train_locomotive_order (train_id, locomotive_id, locomotive_order)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1)
