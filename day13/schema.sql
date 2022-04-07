-- drop database if exists
drop schema if exists address_book;

-- create a new database
create schema address_book;

-- select database
use address_book;

-- create table
create table bff (
    email varchar(128) not null,
    name varchar(128) not null,
    phone varchar(16), 
    dob date,
    status enum('friend', 'foe') default 'friend',
    pass_phrase varchar(128),
    
    primary key(email)
);
