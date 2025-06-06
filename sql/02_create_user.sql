-- ユーザ作成
create user 'devuser'@'localhost' identified by 'invpass';
grant all privileges on inventory_db.* to 'devuser'@'localhost';
flush privileges;

SELECT user, host FROM mysql.user;

-- DROP DATABASE inventory_db;
