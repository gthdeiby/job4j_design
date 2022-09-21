create table car_bodies(
id serial primary key,
name text
);

create table car_engines(
id serial primary key,
name text
);

create table car_transmissions(
id serial primary key,
name text
);

create table cars(
id serial primary key,
name text,
car_bodies_id int references car_bodies(id),
car_engines_id int references car_engines(id),
car_transmissions_id int references car_transmissions(id)
);

insert into car_bodies(name)
values ('седан'),
       ('купе'),
       ('универсал'),
       ('внедорожник'),
       ('пикап'),
       ('минивэн');

insert into car_engines(name)
values ('бензиновый'),
       ('дизельный'),
       ('электрический'),
       ('водородный');

insert into car_transmissions(name)
values ('механическая'),
       ('автоматическая'),
       ('вариатор'),
       ('роботизированная');

insert into cars(name, car_bodies_id, car_engines_id, car_transmissions_id)
values ('Ford Explorer', 4, 1, 2),
       ('Geep Grand Cherokee', 4, 2, 1),
       ('Ford Mustang', 2, 1, 4),
       ('Audi A5', 2, null, null),
       ('Dodge RAM', 5, 2, null),
       ('Toyota Camry', 1, 1, 2),
       ('Toyota Hilux', 5, 2, 1);

-- Список всех машин и все привязанные к ним детали
select c.name Модель, cb.name Кузов, ce.name Двигатель, ct.name Трансмиссия
from cars c
left join car_bodies cb on c.car_bodies_id = cb.id
left join car_engines ce on c.car_engines_id = ce.id
left join car_transmissions ct on c.car_transmissions_id = ct.id;

-- Вывести кузовы, которые не используются НИ в одной машине
select cb.name from car_bodies cb
left join cars c on c.car_bodies_id = cb.id
where c.name is null;

-- Вывести двигатели, которые не используются НИ в одной машине
select ce.name from car_engines ce
left join cars c on c.car_engines_id = ce.id
where c.name is null;

-- Вывести трансмиссии, которые не используются НИ в одной машине
select ct.name from car_transmissions ct
left join cars c on c.car_transmissions_id = ct.id
where c.name is null;