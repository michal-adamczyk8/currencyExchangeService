drop table if exists users;
drop table if exists accounts;
create table users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250),
    last_name VARCHAR(250),
    password VARCHAR(250),
    pesel VARCHAR(11),
    account INT
);
create table accounts(
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    balance DECIMAL(20, 2),
    currency VARCHAR(3)
)
