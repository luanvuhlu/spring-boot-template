--liquibase formatted sql

--changeset init_example_table:1
CREATE TABLE example_table (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);
