delete from category;
delete from product;

alter table category auto_increment=1;
alter table product auto_increment=1;

insert into category (name) values("Limpeza"),("Alimentação"),("Cosmético"),("Bebida");

insert into product (name, description, category_id, measurement_unit, purchise_price,
reseller_price, price) values
('coca-cola','refrigerante coca-cola',4,'Ml',200,220,250),
('spagetti','massa spagetti',1,'G',110,120,140),
('perfume jonh wilker','sem descrição',4,'Ml',1500,1799,1899),
('Leite condensado Moça','sem descrição',1,'Ml',750,730,790);