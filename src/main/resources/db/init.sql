-- DROP DATABASE delivery;

CREATE DATABASE IF NOT EXISTS delivery;
USE delivery;

CREATE TABLE IF NOT EXISTS clients (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NULL DEFAULT NULL,
    email VARCHAR(45) NULL DEFAULT NULL,
    phone VARCHAR(45) NOT NULL,
    address VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS couriers (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NULL DEFAULT NULL,
    phone VARCHAR(45) NOT NULL,
    vehicleInfo VARCHAR(45) NULL DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS orders (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    orderDate DATETIME NOT NULL,
    status VARCHAR(25) NOT NULL,
    totalPrice DECIMAL(6 , 2 ) NOT NULL,
    clientsId INT NOT NULL,
    FOREIGN KEY (clientsID)
        REFERENCES clients (id)
);

CREATE TABLE IF NOT EXISTS deliveries (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    pickupTime DATETIME NOT NULL,
    deliveryTime DATETIME NULL DEFAULT NULL,
    deliveryStatus VARCHAR(25) NOT NULL,
    distanceKm DOUBLE NOT NULL,
    couriersId INT NOT NULL,
    ordersId INT NOT NULL,
    FOREIGN KEY (couriersId)
        REFERENCES couriers (id),
    FOREIGN KEY (ordersId)
        REFERENCES orders (id)
);

CREATE TABLE IF NOT EXISTS restaurants (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(45) NOT NULL,
    address VARCHAR(45) NOT NULL,
    phone VARCHAR(45) NOT NULL
);

CREATE TABLE IF NOT EXISTS menus (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    restaurantsId INT NOT NULL,
    FOREIGN KEY (restaurantsId)
        REFERENCES restaurants (id)
);

CREATE TABLE IF NOT EXISTS dishes (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(45) NOT NULL,
    description VARCHAR(110) NULL DEFAULT NULL,
    price DECIMAL(6 , 2 ) NOT NULL,
    menusId INT NOT NULL,
    FOREIGN KEY (menusId)
        REFERENCES menus (id)
);

CREATE TABLE IF NOT EXISTS payments (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    paymentMethod VARCHAR(15) NOT NULL,
    paymentStatus VARCHAR(25) NOT NULL,
    paidAt DATETIME NULL DEFAULT NULL,
    ordersId INT NOT NULL,
    FOREIGN KEY (ordersId)
        REFERENCES orders (id)
);

CREATE TABLE IF NOT EXISTS promotions (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    description VARCHAR(150) NULL DEFAULT NULL,
    validUntil DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS ratings (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    score INT UNSIGNED NULL DEFAULT NULL,
    comment VARCHAR(1000) NULL DEFAULT NULL,
    clientsId INT NOT NULL,
    couriersId INT NOT NULL,
    ordersId INT NOT NULL,
    FOREIGN KEY (clientsId)
        REFERENCES clients (id),
    FOREIGN KEY (couriersId)
        REFERENCES couriers (id),
    FOREIGN KEY (ordersId)
        REFERENCES orders (id)
);

CREATE TABLE IF NOT EXISTS restaurant_employees (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NULL DEFAULT NULL,
    role VARCHAR(45) NOT NULL,
    restaurantsId INT NOT NULL,
    FOREIGN KEY (restaurantsId)
        REFERENCES restaurants (id)
);

CREATE TABLE IF NOT EXISTS support_tickets (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    issue VARCHAR(45) NOT NULL,
    status VARCHAR(45) NOT NULL,
    createdAt DATETIME NOT NULL,
    resolvedAt DATETIME NULL DEFAULT NULL,
    clientsId INT NOT NULL,
    ordersId INT NOT NULL,
    FOREIGN KEY (clientsId)
        REFERENCES clients (id),
    FOREIGN KEY (ordersId)
        REFERENCES orders (id)
);

CREATE TABLE IF NOT EXISTS vehicles (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    type VARCHAR(25) NOT NULL,
    plateNumber VARCHAR(15) NOT NULL,
    couriersId INT NOT NULL,
    FOREIGN KEY (couriersId)
        REFERENCES couriers (id)
);

CREATE TABLE IF NOT EXISTS orders_has_dishes (
    ordersId INT NOT NULL,
    dishesId INT NOT NULL,
    quantity INT UNSIGNED NOT NULL,
    FOREIGN KEY (ordersId)
        REFERENCES orders (id),
    FOREIGN KEY (dishesId)
        REFERENCES dishes (id)
);

CREATE TABLE IF NOT EXISTS orders_has_promotions (
    ordersId INT NOT NULL,
    promotionsId INT NOT NULL,
    FOREIGN KEY (ordersId)
        REFERENCES orders (id),
    FOREIGN KEY (promotionsId)
        REFERENCES promotions (id)
);
  
  
  INSERT INTO clients(name, lastName, email, phone, address) VALUES
  ('Oleksandr', 'Korneliuk', 'skorneliuk@gmail.com', '0967335881', 'Saint Garmen str. 2'),
  ('Vadym', NULL, 'vskryp@gmail.com', '0684856189', 'Glory str. 221B'),
  ('Viktoriia', 'Shcherbatiuk', NULL, '0974537882', 'Mykhaila Maksymovycha str 44A');
  
  INSERT INTO couriers(name, lastName, phone, vehicleInfo) VALUES
  ('Ivan', 'Kovalenko', '+380501111222', 'Toyota Prius'),
  ('Olena', 'Marchenko', '+380671234567', NULL),
  ('Andriy', NULL, '+380931112233', 'Bicycle');
  
  INSERT INTO orders(orderDate, status, totalPrice, clientsId) VALUES
  ('2025-08-01 12:15:00', 'delivered', 380.00, 1),
  ('2025-08-02 14:00:00', 'on the way', 270.00, 2),
  ('2025-08-03 18:30:00', 'pending', 200.00, 3),
  ('2025-08-02 13:00:00', 'on the way', 414.99, 1),
  ('2025-08-03 18:30:00', 'pending', 360.00, 1),
  ('2025-08-02 17:36:00', 'pending', 420.50, 2);
  
  INSERT INTO deliveries(pickupTime, deliveryTime, deliveryStatus, distanceKm, couriersId, ordersId) VALUES
  ('2025-08-01 12:30:00', '2025-08-01 13:00:00', 'completed', 5.2, 1, 1),
  ('2025-08-02 14:15:00', NULL, 'on the way', 3.8, 2, 2),
  ('2025-08-01 17:00:00', '2025-08-01 17:25:00', 'pending', 4.4, 3, 3),
  ('2025-08-01 12:30:00', '2025-08-01 13:00:00', 'completed', 5.2, 1, 4),
  ('2025-08-02 14:15:00', NULL, 'on the way', 3.8, 2, 5),
  ('2025-08-01 17:00:00', '2025-08-01 17:25:00', 'pending', 4.4, 3, 6);
  
  INSERT INTO restaurants(name, address, phone) VALUES
  ('Pizza House', 'Kyiv, Lva Tolstoho 15', '+380501111111'),
  ('Sushi World', 'Lviv, Franka 20', '+380672222222'),
  ('Burger Hub', 'Odesa, Panteleimonivska 30', '+380933333333');
  
  INSERT INTO menus(restaurantsId) VALUES
  (1), 
  (2), 
  (3);
  
  INSERT INTO dishes(name, description, price, menusId) VALUES
  ('Margherita Pizza', 'Cheese, tomato, basil', 180.00, 1),
  ('Pepperoni Pizza', 'Cheese, tomato, pepperoni', 200.00, 2),
  ('California Roll', 'Crab, avocado, cucumber', 150.00, 3),
  ('Salmon Nigiri', 'Rice, salmon', 120.00, 1),
  ('Cheeseburger', 'Beef, cheese, lettuce', 140.00, 2),
  ('French Fries', 'Potatoes, salt', 60.00, 3),
  ('Cinnabon', 'Sweet, luscious flavor', 120.00, 1);
  
  INSERT INTO payments(paymentMethod, paymentStatus, paidAt, ordersId) VALUES
  ('card', 'paid', '2025-08-01 12:16:00', 1),
  ('cash', 'pending', NULL, 2),
  ('card', 'pending', NULL, 3);
  
  INSERT INTO promotions(description, validUntil) VALUES
  ('10% for first order', '2025-12-31'),
  ('Free delivery for orders over 300 UAH', '2025-09-30');
  
  INSERT INTO ratings(score, comment, clientsId, couriersId, ordersId) VALUES
  (5, 'Fast delivery!', 1, 1, 1),
  (4, 'Tasty but a bit late', 2, 2, 2);
  
  INSERT INTO restaurant_employees(name, lastName, role, restaurantsId) VALUES
  ('Marco', 'Rossi', 'Cook', 1),
  ('Olha', 'Sydorenko', 'Manager', 1),
  ('Takashi', 'Ito', 'Cook', 2),
  ('James', 'Lee', 'Cook', 3);
  
  INSERT INTO support_tickets(issue, status, createdAt, resolvedAt, clientsId, ordersId) VALUES
  ('Pizza was cold', 'resolved', '2025-08-01 13:05:00', '2025-08-01 14:00:00', 1, 1),
  ('Missing chopsticks', 'open', '2025-08-02 14:30:00', NULL, 2, 2);
  
  INSERT INTO vehicles(type, plateNumber, couriersId) VALUES
  ('car', 'AA1234BB', 1),
  ('scooter', 'BC5678CD', 2),
  ('bicycle', 'AB4799BB', 3);
  
  INSERT INTO orders_has_dishes(ordersID, dishesId, quantity) VALUES
  (1, 1, 1),
  (1, 2, 1),
  (2, 3, 2),
  (2, 4, 1),
  (3, 5, 1),
  (3, 6, 4);
  
  INSERT INTO orders_has_promotions(ordersId, promotionsId) VALUES
  (1, 1),
  (2, 2);
