CREATE DATABASE expense;

CREATE SEQUENCE category_seq INCREMENT 50;

create table category (
    id serial primary key,
    name varchar(255) not null
)

CREATE SEQUENCE category_pattern_seq INCREMENT 50;

create table category_pattern (
    id serial primary key,
    category_id int not null,
    pattern varchar(255) not null
)