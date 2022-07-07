delete from category;
delete from product;
delete from sale;
delete from sale_item;


alter table category auto_increment=1;
alter table product auto_increment=1;
alter table sale auto_increment=1;
alter table sale_item auto_increment=1;

insert into category (name) values("Limpeza"),("Alimentação"),("Cosmético"),("Bebida");

insert into product (name, description, category_id, measurement_unit, purchise_price,
reseller_price, price,available_unities) values
('coca-cola','refrigerante coca-cola',4,'Ml',200,220,250,50),
('spagetti','massa spagetti',1,'G',110,120,140,75),
('perfume jonh wilker','sem descrição',4,'Ml',1500,1799,1899,5),
('Leite condensado Moça','sem descrição',1,'Ml',750,730,790,12);

insert into product (name, description, category_id, measurement_unit, purchise_price,
reseller_price, price) values
('Leite Nido gordo','sem descrição',1,'G',2250,2200,2500);

insert into sale(payment_mode, client_type, amount_paid, transshipment, total)
values('MONEY','CONSUMER',800,50,750),('POS','CONSUMER',3000,181,2819)
,('MONEY','RESELLER',27000,15,26985),('POS','CONSUMER',3950,0,3950), ('MONEY','CONSUMER',2000,280,1720);

insert into sale_item(sale_id,product_id,quantity,total)values
(1,1,3,750),
(2,1,2,500),(2,4,1,1899),(2,2,3,420),
(3,3,15,26985),
(4,4,5,3950),
(5,4,2,1580),(5,2,1,140);