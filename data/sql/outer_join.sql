create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);

insert into departments(name) values ('Конструкторское бюро');
insert into departments(name) values ('ОТК');
insert into departments(name) values ('Производство');
insert into departments(name) values ('ИТ');
insert into departments(name) values ('Бухгалтерия');

insert into employees(name, departments_id) values ('Афанасий', 1);
insert into employees(name, departments_id) values ('Дильшод', 1);
insert into employees(name, departments_id) values ('Артур', 1);
insert into employees(name, departments_id) values ('Самсон', 3);
insert into employees(name, departments_id) values ('Сергей', 3);
insert into employees(name, departments_id) values ('Роза', 5);
insert into employees(name, departments_id) values ('Феофан', null);


-- Запросы с left, right, full, cross соединениями
select * from employees e
left join departments d
on e.departments_id = d.id;

select * from employees e
right join departments d
on e.departments_id = d.id;

select * from employees e
full join departments d
on e.departments_id = d.id;

select * from employees e
cross join departments d;

-- Департаменты, у которых нет работников
select d.name from departments d
left join employees e
on e.departments_id = d.id
where e.name is null;

-- Запросы, которые давали бы одинаковый результат
select e.name "Имя", d.name "Отдел" from employees e
left join departments d
on e.departments_id = d.id;

select e.name "Имя", d.name "Отдел" from departments d
right join employees e
on e.departments_id = d.id;

select d.name "Отдел", e.name "Имя" from departments d
left join employees e
on e.departments_id = d.id;

select d.name "Отдел", e.name "Имя" from employees e
right join departments d
on e.departments_id = d.id;

-- Teens
create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values
('Иван', 'Муж'),
('Пётр', 'Муж'),
('Софья', 'Жен'),
('Ксения', 'Жен'),
('Василиса', 'Жен'),
('Ольга', 'Жен');

-- Все возможные разнополые пары
select t1.name, t2.name from teens t1
cross join teens t2
where t1.gender = 'Муж' and t2.gender = 'Жен';