create table collectors (
    id serial primary key,
    name varchar(50)
);

insert into collectors (name) values ('Иван Иванов');
insert into collectors (name) values ('Петр Петров');
insert into collectors (name) values ('Сидор Сидоров');

create table brands (
    id serial primary key,
    name varchar(50)
);

insert into brands (name) values ('Ferrari');
insert into brands (name) values ('Lamborghini');
insert into brands (name) values ('Bugatti');

create table models (
    id serial primary key,
    name varchar(50),
    brands_id int references brands(id)
);

insert into models (name, brands_id) values ('California', 1);
insert into models (name, brands_id) values ('Portofino', 1);
insert into models (name, brands_id) values ('Roma', 1);
insert into models (name, brands_id) values ('Aventador', 2);
insert into models (name, brands_id) values ('Gallardo', 2);
insert into models (name, brands_id) values ('Diablo', 2);
insert into models (name, brands_id) values ('Veyron', 3);
insert into models (name, brands_id) values ('Chiron', 3);

create table garage (
    id serial primary key,
    collectors_id int references collectors(id),
    models_id int references models(id)
);

insert into garage (collectors_id, models_id) values (1, 1);
insert into garage (collectors_id, models_id) values (1, 4);
insert into garage (collectors_id, models_id) values (1, 7);
insert into garage (collectors_id, models_id) values (2, 7);
insert into garage (collectors_id, models_id) values (2, 8);
insert into garage (collectors_id, models_id) values (3, 4);
insert into garage (collectors_id, models_id) values (3, 5);
insert into garage (collectors_id, models_id) values (3, 6);
insert into garage (collectors_id, models_id) values (3, 7);
insert into garage (collectors_id, models_id) values (3, 8);

create view show_collectors_with_less_then_2_models
    as select c.name Collector, count(b.name), b.name Brand
    from collectors c
    join garage g on c.id = g.collectors_id
    join models m on m.id = g.models_id
    join brands b on b.id = m.brands_id
    group by (c.name, b.name) having count(b.name) < 2;

select * from show_collectors_with_less_then_2_models;