/**
one-to-many
*/
create table city(
    id serial primary key,
    name varchar(255)
);

create table residents(
    id serial primary key,
    name varchar(255),
    position_id int references city(id)
);

/**
many-to-many
*/
 create table users(
     id serial primary key,
     name varchar(255)
 );

 create table channels(
     id serial primary key,
     name varchar(255)
 );

 create table users_channels(
     id serial primary key,
     user_id int references users(id),
     cnannel_id int references channels(id)
 );

/**
one-to-one
*/
create table nick(
    id serial primary key,
    name varchar(255)
);

create table people(
    id serial primary key,
    name varchar(255),
    nick_id int references nick(id) unique
);