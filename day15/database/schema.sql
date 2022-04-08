--- drop database if exists
drop schema if exists day15db;

create schema day15db;

use day15db;

create table users (
    username varchar(32) not null,
    password varchar(256) not null,
    primary key(username)
);