delete from category;
delete from product;
delete from sale;
delete from sale_item;


alter table category auto_increment=1;
alter table product auto_increment=1;
alter table sale auto_increment=1;
alter table sale_item auto_increment=1;

insert into category (name) values("Limpeza"),("Alimentação"),("Cosmético"),("Bebida"),("Material escolar")
,("Material de contrução"),("Vestuário");

insert into product (name, description, category_id, measurement_unit, purchise_price,
 price,available_unities) values
('coca-cola','refrigerante coca-cola',4,'ml',200,250,50),
('spagetti','massa spagetti',1,'g',110,140,75),
('perfume jonh wilker','sem descrição',1,'ml',1500,1899,5),
('Mozarella','sem descrição',1,'g',2500.80,2800.79,12),
('Leite condensado Moça','sem descrição',1,'ml',750,790,12);

insert into product (name, description, category_id, measurement_unit, purchise_price, price) values
('Leite Nido gordo','sem descrição',1,'g',2250,2500);

insert into sale(payment_mode, amount_paid, transshipment, total)
values('MONEY',800,50,750),('POS',3000,181,2819)
,('MONEY',27000,15,26985),('POS',3950,0,3950), ('MONEY',2000,280,1720);

insert into sale_item(sale_id,product_id,quantity,total)values
(1,1,3,750),(2,1,2,500),(2,4,1,1899),
(2,2,3,420),(3,3,15,26985),(4,4,5,3950),
(5,4,2,1580),(5,2,1,140);