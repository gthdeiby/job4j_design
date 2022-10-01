create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure delete_data(d_id integer)
language 'plpgsql'
as $$
    BEGIN
        delete from products where id = d_id;
    END;
$$;

create or replace function f_delete_data()
returns void
language 'plpgsql'
as $$
    BEGIN
        delete from products where count = 0;
    END;
$$;