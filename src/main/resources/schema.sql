drop table if exists users;

create table users (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    email VARCHAR(256) UNIQUE,
    password VARCHAR(256) NOT NULL
    );

drop table if exists invoices;

create table invoices (
    id LONG AUTO_INCREMENT PRIMARY KEY, 
    company_Name VARCHAR(256) ,
    amount NUMERIC(38, 2),
    date DATE, 
    user_Id LONG,
    FOREIGN KEY(user_Id) REFERENCES users(id)
    );