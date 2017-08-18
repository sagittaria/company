-- 这是自己做Spring MVC的增删查改应用

drop database company;

create database company;
use company;

create table EmployeeTbl
(
id int not null AUTO_INCREMENT,
name varchar(255),
email varchar(255),
age int(11),
primary key (id)
); 
insert into EmployeeTbl(name,email,age) values('甲姓名','a0@b.c',10);
insert into EmployeeTbl(name,email,age) values('甲姓名','a1@b.c',12);
insert into EmployeeTbl(name,email,age) values('乙姓名','d0@e.f',12);
insert into EmployeeTbl(name,email,age) values('乙姓名','d1@e.f',16);
insert into EmployeeTbl(name,email,age) values('乙姓名','d2@e.f',17);
insert into EmployeeTbl(name,email,age) values('乙姓名','d3@e.f',23);
insert into EmployeeTbl(name,email,age) values('丙姓名','g0@h.i',17);
insert into EmployeeTbl(name,email,age) values('丁姓名','j0@k.l',17);
insert into EmployeeTbl(name,email,age) values('丁姓名','j1@k.l',17);
insert into EmployeeTbl(name,email,age) values('丁姓名','j2@k.l',23);

--    select age, count(*) from employeetbl group by age;
--      +------+----------+
--      | age  | count(*) |
--      +------+----------+
--      |   10 |        1 |
--      |   12 |        2 |
--      |   16 |        1 |
--      |   17 |        4 |
--      |   23 |        2 |
--      +------+----------+