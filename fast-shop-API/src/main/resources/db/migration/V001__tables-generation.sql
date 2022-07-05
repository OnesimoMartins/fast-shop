create table category(
id SMALLINT not null auto_increment,
name varchar(100) not null,
PRIMARY KEY(id)
) ENGINE=innodb DEFAULT charset=utf8;

create table product(
id BIGINT not null auto_increment,
name varchar(100) not null,
image VARCHAR(200) null,
available_unities int not null default 0,
description varchar(300) not null default 'sem descrição',
category_id SMALLINT not null REFERENCES category(id),
measurement_unit varchar(5) not null,
purchise_price Decimal(12,2) not null,
reseller_price Decimal(12,2) not null ,
price Decimal(12,2) not null,
PRIMARY KEY(id)
) engine =innoDB DEFAULT CHARSET=utf8;


create table sale(
id BIGINT not null auto_increment ,
date timestamp DEFAULT( CURRENT_TIMESTAMP()),
status enum ('CANCELED','PROCESSING','CONFIRMED') not null default 'PROCESSING',
payment_mode enum('POS','MONEY') not null,
client_type  enum('CONSUMER','RESELLER') not null,
amount_paid NUMERIC(12,2) not null,
transshipment NUMERIC(12,2) not null,
total NUMERIC(12,2) not null,
primary key(id)
) ENGINE=innodb DEFAULT charset=utf8;

create table sale_products(
sale_id BIGINT REFERENCES sale(id),
product_id BIGINT REFERENCES product(id),
quantity int not null,
total NUMERIC(12,2) not null
) ENGINE=innodb DEFAULT charset=utf8;