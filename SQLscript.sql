CREATE DATABASE outlet;

USE outlet;

CREATE TABLE stores(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name_store VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE products(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name_product VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    id_store INT,
    FOREIGN KEY (id_store) REFERENCES stores(id) ON DELETE CASCADE    
);

CREATE TABLE customers(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name_customer VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE shoppings(
	id INT PRIMARY KEY AUTO_INCREMENT,
    id_customer INT,
    id_product INT,
    FOREIGN KEY (id_customer) REFERENCES customers(id) ON DELETE CASCADE,
    FOREIGN KEY (id_product) REFERENCES products(id) ON DELETE CASCADE,
    date_shopping DATE NOT NULL,
    quantity INT NOT NULL
);

ALTER TABLE outlet.products
ADD COLUMN stock INT NOT NULL;

INSERT INTO stores(name_store, address) VALUES
('Koaj', 'Local130'),
('Mimos', 'Burbuja01'),
('LiliPink', 'Local129'),
('AguaMarina', 'Local103'),
('Tostado', 'Local109');

SELECT * FROM products;

SELECT * FROM products
INNER JOIN stores ON stores.id = products.id_store;

SELECT * FROM shoppings
INNER JOIN customers ON customers.id = shoppings.id_customer
INNER JOIN products ON products.id = shoppings.id_product;