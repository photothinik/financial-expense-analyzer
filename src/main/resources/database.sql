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

CREATE SEQUENCE exp_record_seq INCREMENT 50;

create table expense_record (
    id serial primary key,
    transaction_dt date not null,
    description varchar(255) not null,
    check_number int,
    amount varchar(15) not null,
    category_override int,
    label varchar(255)
)