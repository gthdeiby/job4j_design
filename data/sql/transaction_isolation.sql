create table cars(
	id serial primary key,
	model text,
	power integer
);

insert into cars(model, power) values('Toyota Carina', 130);
insert into cars(model, power) values('Dodge Challenger', 330);
insert into cars(model, power) values('Subaru Impreza', 280);

begin transaction;

select * from cars;

insert into cars(model, power) values('Chevrolet Tahoe', 353);
delete from cars where power = 280;
update cars set power = 133 where model = 'Toyota Carina';

select * from cars;

commit;

begin transaction isolation level repeatable read;

insert into cars(model, power) values('Subaru Impreza', 280);
delete from cars where power = 353;
update cars set power = 130 where model = 'Toyota Carina';

commit;

begin transaction isolation level serializable;

select sum(power) from cars;

update cars set power = 133 where model = 'Toyota Carina';

select sum(power) from cars;

update cars set power = 300 where model = 'Dodge Challenger';

commit;

