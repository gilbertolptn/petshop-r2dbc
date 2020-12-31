CREATE TABLE if not exists ANIMAL (
     id IDENTITY NOT NULL PRIMARY KEY,
     name VARCHAR(50) NOT NULL,
     birth timestamp NOT NULL
);

CREATE TABLE if not exists PRODUCT (
     id IDENTITY NOT NULL PRIMARY KEY,
     name VARCHAR(50) NOT NULL
);

CREATE TABLE if not exists CLIENT (
     id IDENTITY NOT NULL PRIMARY KEY,
     first_name VARCHAR(50) NOT NULL,
     last_name VARCHAR(50) NOT NULL,
     document VARCHAR(20) NOT NULL
);