-- 这是自己做Spring MVC的增删查改应用

drop database company;

create database company;
use company;

create table EmployeeTbl
(
id int not null AUTO_INCREMENT,
name varchar(255),
email varchar(255),
primary key (id)
); 
insert into EmployeeTbl(name,email) values('甲姓名','a@b.c');
insert into EmployeeTbl(name,email) values('乙姓名','d@e.f');
insert into EmployeeTbl(name,email) values('丙姓名','g@h.i');
insert into EmployeeTbl(name,email) values('丁姓名','j@k.l');
