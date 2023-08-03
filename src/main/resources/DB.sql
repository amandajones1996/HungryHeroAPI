CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY, 
    restaurant VARCHAR(255) UNIQUE NOT NULL,
    food_order TEXT NOT NULL,
    delivery_freq TEXT NOT NULL,
    total_amount NUMERIC(10, 2),
    user_id INTEGER NOT NULL, 
    CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
        REFERENCES users (id)
);