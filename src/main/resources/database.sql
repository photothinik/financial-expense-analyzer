CREATE DATABASE expense;

CREATE SEQUENCE category_seq INCREMENT 50;

create table category (
    id serial primary key,
    name varchar(255) not null
)