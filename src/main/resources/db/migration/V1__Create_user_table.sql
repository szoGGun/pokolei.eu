create table users
(
    user_id    serial primary key,
    username  varchar(20),
    first_name varchar(100),
    last_name  varchar(100),
    password   varchar(500)
);

insert into users (username, first_name, last_name, password)
values ('admin', 'John', 'Doe', '{bcrypt}$2a$12$/WhzpNO/PUkAr4vVrD59su7pqlQlLVhN8l0TavY9aG2b8dKsEcblK');
