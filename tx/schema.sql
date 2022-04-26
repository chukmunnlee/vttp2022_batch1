drop database if exists bank;

create database bank;

use bank;

create table account (
    acct_id char(8) not null,
    balance decimal(5, 2) not null,
    primary key(acct_id)
);