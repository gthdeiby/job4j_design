create table cars(
	id serial primary key,
	model text,
	power integer
);

insert into cars(model, power) values('Toyota Carina', 130);
insert into cars(model, power) values('Dodge Challenger', 330);
insert into cars(model, power) values('Subaru Impreza', 280);

begin transaction;
savepoint first;
insert into cars(model, power) values('Chevrolet Tahoe', 353);
select * from cars;
rollback to first;
select * from cars;
savepoint second;
delete from cars where power = 280;
select * from cars;
rollback to second;
select * from cars;
savepoint third;
update cars set power = 133 where model = 'Toyota Carina';
select * from cars;
rollback to third;
select * from cars;
release savepoint second;
commit;