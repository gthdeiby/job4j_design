create table city(
    id serial primary key,
    name varchar(255),
    code int
);

create table residents(
    id serial primary key,
    name varchar(255),
    city_id int references city(id)
);

insert into city(name, code) values ('Tambov', 68);
insert into city(name, code) values ('Lipetsk', 48);
insert into city(name, code) values ('Kaluga', 40);
insert into city(name, code) values ('Moscow', 99);

insert into residents(name, city_id) values ('Ekaterina', 1);
insert into residents(name, city_id) values ('Nikita', 2);
insert into residents(name, city_id) values ('Gleb', 3);
insert into residents(name, city_id) values ('Afanasy', 4);
insert into residents(name) values ('Dmitry');

select r.name, c.name, c.code
from residents as r join city as c on r.city_id = c.id;

select r.name Имя, c.name Город
from residents r join city c on r.city_id = c.id;

select r.name as "Имя жителя", c.code as "Код региона"
from residents r join city c on r.city_id = c.id;