create table cars(
	id serial primary key,
	model text,
	power integer,
	color varchar(255)
);

insert into cars(model, power, color)
values('Toyota Carina', 130, 'aquamarine')

update cars set power = 133;

delete from cars;
