CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL
);