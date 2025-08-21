package com.solvd.delivery.dao.impl;

import com.solvd.delivery.dao.interfaces.IMySQLDAO;
import com.solvd.delivery.model.Dish;
import com.solvd.delivery.model.Order;
import com.solvd.delivery.util.ConnectionPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IMySQLDAO<Order> {
    @Override
    public Order getById(int id) throws SQLException {
        Order selectedOrder = null;

        String sql = "SELECT * FROM orders WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        ConnectionPool.releaseConnection(connection);
        while (resultSet.next()) {
            selectedOrder = new Order(
                    resultSet.getInt("id"),
                    resultSet.getObject("orderDate", LocalDateTime.class),
                    resultSet.getString("status"),
                    resultSet.getBigDecimal("totalPrice"),
                    resultSet.getInt("clientsId")
            );
        }

        return selectedOrder;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ConnectionPool.releaseConnection(connection);
        while (resultSet.next()) {
            orders.add(new Order(
                    resultSet.getInt("id"),
                    resultSet.getObject("orderDate", LocalDateTime.class),
                    resultSet.getString("status"),
                    resultSet.getBigDecimal("totalPrice"),
                    resultSet.getInt("clientsId")
            ));
        }

        return orders;
    }

    @Override
    public void save(Order entity) throws SQLException {
        String sql = "INSERT INTO orders(orderDate, status, totalPrice, clientsId) VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, entity.orderDate());
        statement.setString(2, entity.status());
        statement.setBigDecimal(3, entity.totalPrice());
        statement.setInt(4, entity.clientId());
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }

    @Override
    public void update(Order entity) throws SQLException {
        String sql = "UPDATE orders SET orderDate = ?, status = ?, totalPrice = ?, clientsId = ? WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, entity.orderDate());
        statement.setString(2, entity.status());
        statement.setBigDecimal(3, entity.totalPrice());
        statement.setInt(4, entity.clientId());
        statement.setInt(5, entity.id());
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }
}
