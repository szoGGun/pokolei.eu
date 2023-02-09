create table users
(
    user_id    serial primary key,
    username  varchar(20),
    password   varchar(500)
);

insert into users (username, password)
values ('admin', '$2a$12$/WhzpNO/PUkAr4vVrD59su7pqlQlLVhN8l0TavY9aG2b8dKsEcblK');
