-- DB選択
use inventory_db;

-- 販売履歴テーブル作成
create table sales (
	sales_id int auto_increment primary key,
    product_id int not null,
    quantity_sold int not null,
    sale_date timestamp default current_timestamp,
    foreign key (product_id) references products(product_id)
);