insert into role(name) values ('empty');
insert into rules(name) values ('empty');
insert into state(name) values ('empty');
insert into category(name) values ('empty');
insert into users(name, role_id) values ('empty', 1);
insert into role_rules(role_id, rules_id) values (1,1);
insert into item(name, users_id, category_id, state_id) values ('empty',1,1,1);
insert into comments(name, item_id) values ('empty',1);
insert into attachs(name,item_id) values ('empty',1);