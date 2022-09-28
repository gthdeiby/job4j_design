create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

-- Триггер расчета налога на уровне запроса после вставки данных

create trigger statement_tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure statement_tax();

create or replace function statement_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.13
        where id = select id from inserted;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- Триггер расчета налога на уровне строки до вставки данных

create trigger row_tax_trigger
    before insert
    on products
    for each row
    execute procedure row_tax();

create or replace function row_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.13
        where id = new.id;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- Триггер вставки данных на уровне строки

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create trigger row_insert_trigger
    instead of insert
    on history_of_price
    for each row
    execute procedure row_insert();

create or replace function row_insert()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        values (new.name, new.price, current_date());
        return new;
    END;
$$
LANGUAGE 'plpgsql';
