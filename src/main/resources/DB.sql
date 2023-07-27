CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY, 
    restuarant VARCHAR(255) UNIQUE NOT NULL,
    food_order TEXT UNIQUE NOT NULL,
    delivery_freq TEXT UNIQUE NOT NULL,
    total_amount NUMERIC(10, 2),
    id INTEGER, 
    CONSTRAINT fk_id
        FOREIGN KEY (id)
        REFERENCES users (id)
);