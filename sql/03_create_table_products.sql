-- DB選択
USE inventory_db;

-- 商品テーブル作成
create table products (
	product_id int auto_increment primary key,
	product_name varchar(100) not null,
    price decimal(10,2) not null ,
    stock_quantity int not null default 0,
    created_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp on update current_timestamp
);	