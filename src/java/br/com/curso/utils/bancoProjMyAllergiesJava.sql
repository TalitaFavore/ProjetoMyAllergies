create table profile(
	id serial primary key,
	username varchar(50),
	email varchar(50),
	passcode varchar(15)
);

insert into profile(username, email, passcode) values('Talita', 'talitafavore@gmail.com', 'teste123');

select * from profile;

--
create table allergy(
	id serial primary key,
	nameAllergy varchar(50)
);
insert into allergy(nameAllergy) values('Amendoim');

select * from allergy;

--
create table intensity(
	id serial primary key,
	nameIntensity varchar(10)
);

insert into intensity(nameIntensity) values('Grave');
insert into intensity(nameIntensity) values('Leve');

select * from intensity;

--
create table product(
	id serial primary key,
	barcode varchar(50),
	brand varchar(50),
	nameProduct varchar(50)
);

insert into product(barcode, brand, nameProduct) values(111111111111, 'SevenBoys', 'Pão de Leite');

select * from product;