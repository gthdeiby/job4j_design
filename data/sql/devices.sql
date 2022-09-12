create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Sony', 6000.0), ('Iphone', 10000.0), ('Samsung', 4000.0);
insert into people(name) values ('Petr'), ('Stas'), ('Vasily');
insert into devices_people(people_id, device_id) values (1, 1), (1, 2), (1, 3);
insert into devices_people(people_id, device_id) values (2, 2), (1, 3);
insert into devices_people(people_id, device_id) values (3, 3);

select avg(price) as "Средняя цена" from devices;

select p.name, avg(d.price)
from devices_people dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from devices_people dp
join people p
on dp.people_id = p.id
join devices d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;

