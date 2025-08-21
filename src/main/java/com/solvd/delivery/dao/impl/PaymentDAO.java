package com.solvd.delivery.dao.impl;

import com.solvd.delivery.dao.interfaces.IMySQLDAO;
import com.solvd.delivery.model.Payment;
import com.solvd.delivery.util.ConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements IMySQLDAO<Payment> {
    @Override
    public Payment getById(int id) throws SQLException {
        Payment selectedPayment = null;

        String sql = "SELECT * FROM payments WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        ConnectionPool.releaseConnection(connection);
        while (resultSet.next()) {
            selectedPayment = new Payment(
                    resultSet.getInt("id"),
                    resultSet.getString("paymentMethod"),
                    resultSet.getString("paymentStatus"),
                    resultSet.getObject("paidAt", LocalDateTime.class),
                    resultSet.getInt("ordersId")
            );
        }

        return selectedPayment;
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ConnectionPool.releaseConnection(connection);
        while (resultSet.next()) {
            payments.add(new Payment(
                    resultSet.getInt("id"),
                    resultSet.getString("paymentMethod"),
                    resultSet.getString("paymentStatus"),
                    resultSet.getObject("paidAt", LocalDateTime.class),
                    resultSet.getInt("ordersId")
            ));
        }

        return payments;
    }

    @Override
    public void save(Payment entity) throws SQLException {
        String sql = "INSERT INTO payments(paymentMethod, paymentStatus, paidAt, ordersId) VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, entity.paymentMethod());
        statement.setString(2, entity.paymentStatus());
        statement.setObject(3, entity.paidAt());
        statement.setInt(4, entity.orderId());
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }

    @Override
    public void update(Payment entity) throws SQLException {
        String sql = "UPDATE payments SET paymentMethod = ?, paymentStatus = ?, paidAt = ?, ordersId = ? WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, entity.paymentMethod());
        statement.setString(2, entity.paymentStatus());
        statement.setObject(3, entity.paidAt());
        statement.setInt(4, entity.orderId());
        statement.setInt(5, entity.id());
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM payments WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }
}
