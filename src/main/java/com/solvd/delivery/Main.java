package com.solvd.delivery;

import com.solvd.delivery.model.*;
import com.solvd.delivery.service.impl.*;
import com.solvd.delivery.service.interfaces.*;
import com.solvd.delivery.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;

public class Main {

    // INFO
    private static final Logger PRINT = LogManager.getLogger("InfoLogger");

    // ERROR, DEBUG
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Client client = new Client(4, "Maksym", "Parfonov",
                "mparfonov@gmail.com", "0684326767", "Saint Jarman str. 45");

        Delivery delivery = new Delivery(7,
                LocalDateTime.of(2025, Month.DECEMBER, 25, 10, 30, 0),
                LocalDateTime.of(2025, Month.DECEMBER, 25, 11, 30, 0),
                "pending", 27.04, 3, 6);

        Dish dish = new Dish(8, "Fish and Chips",
                "Fried fish and potato", BigDecimal.valueOf(24.50), 3
        );

        Menu menu = new Menu(4, 4);

        Order order = new Order(7,
                LocalDateTime.of(2025, Month.DECEMBER, 25, 10, 30, 0),
                "pending", BigDecimal.valueOf(30.0), 3);

        Payment payment = new Payment(4, "cash", "pending",
                LocalDateTime.of(2025, Month.DECEMBER, 25, 11, 30, 0), 6);

        Rating rating = new Rating(3, 4, null, 3, 2, 6);

        try {
            testClientService(client);
            testDeliveryService(delivery);
            testOrderService(order);
            testPaymentService(payment);
            testRatingService(rating);

            ConnectionPool.closeAllConnections();
        } catch (SQLException e) {
            LOG.error(e);
        }
    }

    private static void testClientService(final Client client) throws SQLException {
        IClientService clientService = new ClientService();

        clientService.getById(2);

        clientService.getAll().forEach(c -> PRINT.info("{} ({})\n", c.name(), c.email()));
        PRINT.info("\n");

        clientService.save(client);
        clientService.update(client);
        clientService.deleteById(client.id());

        clientService.getAll().forEach(c -> PRINT.info("{} ({})\n", c.name(), c.email()));
        PRINT.info("\n");
    }

    private static void testDeliveryService(final Delivery delivery) throws SQLException {
        IDeliveryService deliveryService = new DeliveryService();

        deliveryService.getById(2);

        deliveryService.getAll().forEach(d -> PRINT.info(
                "Delivery Id: {}\tDelivery status: {}\n", d.id(), d.deliveryStatus()
        ));
        PRINT.info("\n");

        deliveryService.save(delivery);
        deliveryService.update(delivery);
        deliveryService.deleteById(delivery.id());

        deliveryService.getAll().forEach(d -> PRINT.info(
                "Delivery Id: {}\tDelivery status: {}\n", d.id(), d.deliveryStatus()
        ));
        PRINT.info("\n");
    }

    private static void testOrderService(Order order) throws SQLException {
        IOrderService orderService = new OrderService();

        orderService.getById(2);

        orderService.getAll().forEach(o -> PRINT.info(
                "Order Id: {}\tOrder status: {}\n", o.id(), o.status()
        ));
        PRINT.info("\n");

        orderService.save(order);
        orderService.update(order);
        orderService.deleteById(order.id());

        orderService.getAll().forEach(o -> PRINT.info(
                "Order Id: {}\tOrder status: {}\n", o.id(), o.status()
        ));
        PRINT.info("\n");
    }

    private static void testPaymentService(Payment payment) throws SQLException {
        IPaymentService paymentService = new PaymentService();

        paymentService.getById(2);

        paymentService.getAll().forEach(p-> PRINT.info(
                "Payment Id: {}\tPayment status: {}\n", p.id(), p.paymentStatus()
        ));
        PRINT.info("\n");

        paymentService.save(payment);
        paymentService.update(payment);
        paymentService.deleteById(payment.id());

        paymentService.getAll().forEach(p-> PRINT.info(
                "Payment Id: {}\tPayment status: {}\n", p.id(), p.paymentStatus()
        ));
        PRINT.info("\n");
    }

    private static void testRatingService(Rating rating) throws SQLException {
        IRatingService ratingService = new RatingService();

        ratingService.getById(2);

        ratingService.getAll().forEach(r->PRINT.info(
                "Rating Id: {}\tScore: {}\n", r.id(), r.score()
        ));
        PRINT.info("\n");

        ratingService.save(rating);
        ratingService.update(rating);
        ratingService.deleteById(rating.id());

        ratingService.getAll().forEach(r->PRINT.info(
                "Rating Id: {}\tScore: {}\n", r.id(), r.score()
        ));
        PRINT.info("\n");
    }
}
