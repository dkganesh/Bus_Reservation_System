drop database buses;
create database BUSES;
use buses;

create table buslist(
busno int primary key auto_increment,
buscap int,
ac boolean,
source varchar(25),
dest varchar(25),
fill int
);

alter table buslist drop fill ;
update buslist set fill=0;

create table passlist(
mail varchar(25) primary key,
pname varchar(25),
phone varchar(10),
pass varchar(8)
);

create table bookinglist(
pname varchar(25),
source varchar(25),
dest varchar(25),
passid varchar(25),
busno int,
foreign key(busno) references buslist(busno),
bdate date 
);

insert into buslist(busno,buscap,ac) values(1,45,true),(2,40,false),(3,50,true),(4,30,true),(5,50,false);
update buslist set dest="Neyveli";
insert into passlist values("dk","dk","dk","dk"); 

alter table bookinglist add tickets int;
 
select * from buslist;
select * from passlist;
select * from bookinglist;
delete from passlist where pname="GANESH";
delete from buslist where busno=30;
delete from bookinglist where busno=1;



