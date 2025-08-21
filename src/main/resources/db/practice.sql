-- LEFT JOIN --
SELECT * FROM clients
LEFT JOIN orders ON clients.id = orders.clientsId;

SELECT * FROM orders
LEFT JOIN deliveries ON orders.id = deliveries.ordersId;

SELECT * FROM couriers
LEFT JOIN vehicles ON couriers.id = vehicles.couriersId;

SELECT * FROM restaurants
LEFT JOIN menus ON restaurants.id = menus.restaurantsId;

SELECT * FROM menus
LEFT JOIN dishes ON menus.id = dishes.menusId;

SELECT * FROM clients
LEFT JOIN ratings ON clients.id = ratings.clientsId;

SELECT * FROM restaurants
LEFT JOIN restaurant_employees ON restaurants.id = restaurant_employees.restaurantsId;

SELECT * FROM orders
LEFT JOIN payments ON orders.id = payments.ordersId;

SELECT * FROM couriers
LEFT JOIN deliveries ON couriers.id = deliveries.couriersId;

-- RIGHT JOIN --
SELECT * FROM clients
RIGHT JOIN orders ON clients.id = orders.clientsId;

SELECT * FROM orders
RIGHT JOIN deliveries ON orders.id = deliveries.ordersId;

SELECT * FROM vehicles
RIGHT JOIN couriers ON vehicles.couriersId = couriers.id;

SELECT * FROM dishes
RIGHT JOIN menus ON dishes.menusId = menus.id;

SELECT * FROM clients
RIGHT JOIN ratings ON clients.id = ratings.clientsId;

SELECT * FROM restaurants
RIGHT JOIN restaurant_employees ON restaurants.id = restaurant_employees.restaurantsId;

SELECT * FROM orders
RIGHT JOIN payments ON orders.id = payments.ordersId;

SELECT * FROM clients
RIGHT JOIN support_tickets ON clients.id = support_tickets.clientsId;

-- INNER JOIN --
SELECT * FROM clients
INNER JOIN orders ON clients.id = orders.clientsId;

SELECT * FROM ((couriers
INNER JOIN deliveries ON couriers.id = deliveries.couriersId)
INNER JOIN orders ON deliveries.ordersId = orders.id);

SELECT * FROM ((restaurants
INNER JOIN menus ON restaurants.id = menus.restaurantsId)
INNER JOIN dishes ON menus.id = dishes.menusId);

SELECT name, lastName, email, phone, address, COUNT(ratings.id) FROM clients
INNER JOIN ratings ON clients.id = ratings.clientsId
GROUP BY ratings.id;

SELECT * FROM ((orders
INNER JOIN orders_has_promotions ON orders.id = orders_has_promotions.ordersId)
INNER JOIN promotions ON orders_has_promotions.promotionsId = promotions.id);

SELECT * FROM couriers
INNER JOIN deliveries ON couriers.id = deliveries.couriersId
WHERE deliveries.deliveryStatus = 'completed';

SELECT restaurants.id, restaurants.name,restaurants.address, restaurants.phone, COUNT(restaurant_employees.restaurantsId) FROM restaurants
INNER JOIN restaurant_employees ON restaurants.id = restaurant_employees.restaurantsID
GROUP BY restaurant_employees.restaurantsID;

SELECT * FROM clients
INNER JOIN support_tickets ON clients.id = support_tickets.clientsId;

-- OUTER JOIN --
SELECT * FROM clients
LEFT JOIN orders ON clients.id = orders.clientsId
UNION
SELECT * FROM clients
RIGHT JOIN orders ON clients.id = orders.clientsId;

SELECT * FROM orders
LEFT JOIN deliveries ON orders.id = deliveries.ordersId
UNION
SELECT * FROM orders
RIGHT JOIN deliveries ON orders.id = deliveries.ordersId;

SELECT * FROM couriers
LEFT JOIN vehicles ON couriers.id = vehicles.couriersId
UNION
SELECT * FROM couriers
RIGHT JOIN vehicles ON couriers.id = vehicles.couriersId;

SELECT * FROM restaurants
LEFT JOIN menus ON restaurants.id = menus.restaurantsId
UNION
SELECT * FROM restaurants
RIGHT JOIN menus ON restaurants.id = menus.restaurantsId;

SELECT * FROM menus
LEFT JOIN dishes ON menus.id = dishes.menusId
UNION
SELECT * FROM menus
RIGHT JOIN dishes ON menus.id = dishes.menusId;

SELECT * FROM clients
LEFT JOIN ratings ON clients.id = ratings.clientsId
UNION
SELECT * FROM clients
RIGHT JOIN ratings ON clients.id = ratings.clientsId;

SELECT * FROM restaurants
LEFT JOIN restaurant_employees ON restaurants.id = restaurant_employees.restaurantsId
UNION
SELECT * FROM restaurants
RIGHT JOIN restaurant_employees ON restaurants.id = restaurant_employees.restaurantsId;

SELECT * FROM orders
LEFT JOIN payments ON orders.id = payments.ordersId
UNION
SELECT * FROM orders
RIGHT JOIN payments ON orders.id = payments.ordersId;

SELECT * FROM couriers
LEFT JOIN deliveries ON couriers.id = deliveries.couriersId
UNION
SELECT * FROM couriers
RIGHT JOIN deliveries ON couriers.id = deliveries.couriersId;

-- 1 Big query --
SELECT
clients.name,
clients.phone,
orders.orderDate,
orders.totalPrice,
restaurants.name,
couriers.name,
vehicles.type,
deliveries.deliveryTime,
deliveries.deliveryStatus,
payments.paymentMethod,
payments.paymentStatus,
promotions.description,
ratings.score,
ratings.comment,
dishes.name,
menus.restaurantsId,
restaurant_employees.lastName,
support_tickets.issue
FROM orders
JOIN clients ON orders.clientsId = clients.id
LEFT JOIN orders_has_dishes ON orders.id = orders_has_dishes.ordersId
LEFT JOIN dishes ON orders_has_dishes.dishesId = dishes.id
LEFT JOIN menus ON dishes.menusId = menus.id
LEFT JOIN restaurants ON menus.restaurantsId = restaurants.id
LEFT JOIN restaurant_employees ON restaurants.id = restaurant_employees.restaurantsId
LEFT JOIN deliveries ON orders.id = deliveries.ordersId
LEFT JOIN couriers ON deliveries.couriersId = couriers.id
LEFT JOIN vehicles ON couriers.id = vehicles.couriersId
LEFT JOIN payments ON orders.id = payments.ordersId
LEFT JOIN orders_has_promotions ON orders.id = orders_has_promotions.ordersId
LEFT JOIN promotions ON orders_has_promotions.promotionsId = promotions.id
LEFT JOIN ratings ON orders.id = ratings.ordersId
LEFT JOIN support_tickets ON orders.id = support_tickets.ordersId
WHERE clients.lastName = 'Korneliuk';

-- GROUP BY (few previous queries have GROUP BY) -- 
SELECT restaurants.name, COUNT(dishes.menusId) FROM restaurants
JOIN menus ON restaurants.id = menus.restaurantsId
JOIN dishes ON menus.id = dishes.menusId
GROUP BY dishes.menusId;

-- HAVING queries --
SELECT clients.id, clients.name, clients.lastName, clients.address, COUNT(orders.clientsId) FROM clients
JOIN orders ON clients.id = orders.clientsId
GROUP BY orders.clientsId
HAVING COUNT(orders.clientsId) > 2;

SELECT couriers.id, couriers.name, couriers.lastName, couriers.phone, couriers.vehicleInfo, SUM(deliveries.distanceKm) FROM couriers
JOIN deliveries ON couriers.id = deliveries.couriersId
GROUP BY deliveries.couriersId
HAVING SUM(deliveries.distanceKm) > 10;

SELECT restaurants.id, restaurants.name, restaurants.address, restaurants.phone, COUNT(dishes.menusId) FROM restaurants
JOIN menus ON restaurants.id = menus.restaurantsId
JOIN dishes ON menus.id = dishes.menusId
GROUP BY dishes.menusId
HAVING COUNT(dishes.menusId) > 2;

-- Queries with AVG / MIN / MAX / SUM functions --
SELECT restaurants.id, restaurants.name, restaurants.address, restaurants.phone, AVG(dishes.price) FROM restaurants
JOIN menus ON restaurants.id = menus.restaurantsId
JOIN dishes ON menus.id = dishes.menusId
GROUP BY dishes.menusId;

SELECT menus.id, dishes.name, dishes.price FROM menus
JOIN dishes ON menus.id = dishes.menusId
WHERE dishes.price = (
SELECT MIN(price)
FROM dishes
WHERE menusId = menus.id
);

SELECT menus.id, dishes.name, dishes.price FROM menus
JOIN dishes ON menus.id = dishes.menusId
WHERE dishes.price = (
SELECT MAX(price)
FROM dishes
WHERE menusId = menus.id
);

SELECT clients.id, clients.name, clients.lastName, clients.address, SUM(orders.totalPrice) FROM clients
JOIN orders ON clients.id = orders.clientsId
GROUP BY orders.clientsId;

SELECT couriers.id, couriers.name, couriers.lastName, couriers.phone, couriers.vehicleInfo, AVG(deliveries.distanceKm) FROM couriers
JOIN deliveries ON couriers.id = deliveries.couriersId
GROUP BY deliveries.couriersId;

SELECT couriers.id, couriers.name, couriers.lastName, couriers.phone, couriers.vehicleInfo, MIN(deliveries.distanceKm) FROM couriers
JOIN deliveries ON couriers.id = deliveries.couriersId
GROUP BY deliveries.couriersId;

SELECT couriers.id, couriers.name, couriers.lastName, couriers.phone, couriers.vehicleInfo, MAX(deliveries.distanceKm) FROM couriers
JOIN deliveries ON couriers.id = deliveries.couriersId
GROUP BY deliveries.couriersId;

SELECT couriers.id, couriers.name, couriers.lastName, couriers.phone, couriers.vehicleInfo, AVG(ratings.score) FROM couriers
JOIN ratings ON couriers.id = ratings.couriersId
GROUP BY ratings.couriersId;

SELECT orders.id, SUM(orders_has_dishes.quantity) FROM orders
JOIN orders_has_dishes ON orders.id = orders_has_dishes.ordersId
GROUP BY orders_has_dishes.ordersId;
