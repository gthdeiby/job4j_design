create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date)
values ('goldfish', 10, date '1111-11-11');
insert into fauna (name, avg_age, discovery_date)
values ('silverfish', 11, date '2010-10-10');
insert into fauna (name, avg_age, discovery_date)
values ('turtle', 15000, date '2011-11-11');
insert into fauna (name, avg_age, discovery_date)
values ('wolf', 50, null);
insert into fauna (name, avg_age, discovery_date)
values ('fox', 50, null);

select * from fauna where name like '%fish%';

select * from fauna where avg_age between 10000 and 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '1950-01-01';