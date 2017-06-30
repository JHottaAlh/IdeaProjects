create table categories (
	goods_category int(3) PRIMARY KEY
	,name VARCHAR(10) UNIQUE
	,inserted_at timestamp
);