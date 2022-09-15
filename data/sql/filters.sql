create table type(
    id serial primary key,
    name text
);

create table product(
    id serial primary key,
    name text,
    type_id int references type(id),
    expired_date date,
    price int
);

insert into type(name) values ('СЫР'), ('МОРОЖЕНОЕ'), ('МОЛОКО'), ('ОВОЩИ');
insert into product(name, type_id, expired_date, price)
values ('Сыр горгонзола', 1, '2031-11-11', 1000),
       ('Сыр дорблю', 1, '2021-11-11', 1100),
       ('Сыр моцарелла', 1, '2022-11-11', 600),
       ('Сыр плавленный', 1, '2022-09-14', 300),
       ('Эскимо', 2, '2022-08-14', 70),
       ('Пломбир', 2, '2022-10-14', 50),
       ('Мороженое крем-брюле', 2, '2022-12-14', 55),
       ('Пастеризованное молоко', 3, '2022-09-20', 80),
       ('Ультрапастеризованное молоко', 3, '2022-10-20', 70),
       ('Картофель', 4, '2023-02-20', 45);

-- Получение всех продуктов с типом "СЫР"
select * from product p join type t on t.id = p.type_id
where t.name = 'СЫР';

-- Получения всех продуктов, у которых в имени есть слово "мороженое"
select * from product where name like 'Мороженое%';

-- Получение всех продуктов, срок годности которых уже истек
select * from product where expired_date < current_date;

-- Получение самого дорогого продукта
select * from product where price = (select max(price) from product);

-- Получение для каждого типа количество продуктов к нему принадлежащих
select t.name Тип, count(t.name) Количество
from product p join type t on t.id = p.type_id
group by t.name;

-- Получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product p join type t on t.id = p.type_id
where t.name = 'СЫР' OR t.name = 'МОЛОКО';

-- Получение типа продуктов, которых осталось меньше 10 штук
select t.name Тип, count(t.name) Количество
from product p join type t on t.id = p.type_id
group by t.name
having count(t.name) < 10;

-- Получение всех продуктов и их типа
select * from product p join type t on t.id = p.type_id;