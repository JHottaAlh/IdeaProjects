create table goods (
	code varchar(12) PRIMARY KEY
	,goods_name varchar(50) UNIQUE NOT NULL
	,category varchar(3) NOT NULL
	,price int(8) NOT NULL
	,maker VARCHAR(10) NOT NULL
	,inserted_at TIMESTAMP
);