package com.solvd.delivery.dao.impl;

import com.solvd.delivery.dao.interfaces.IDeliveryDAO;
import com.solvd.delivery.model.Delivery;
import com.solvd.delivery.util.ConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAO implements IDeliveryDAO {
    @Override
    public Delivery getById(int id) throws SQLException, IOException {
        Delivery selectedDelivery = null;

        String sql = "SELECT * FROM deliveries WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        ConnectionPool.getInstance().releaseConnection(connection);
        while (resultSet.next()) {
            selectedDelivery = new Delivery(
                    resultSet.getInt("id"),
                    resultSet.getObject("pickupTime", LocalDateTime.class),
                    resultSet.getObject("deliveryTime", LocalDateTime.class),
                    resultSet.getString("deliveryStatus"),
                    resultSet.getDouble("distanceKm"),
                    resultSet.getInt("couriersId"),
                    resultSet.getInt("ordersId")
            );
        }

        return selectedDelivery;
    }

    @Override
    public List<Delivery> getAll() throws SQLException, IOException {
        List<Delivery> deliveries = new ArrayList<>();
        String sql = "SELECT * FROM deliveries";
        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ConnectionPool.getInstance().releaseConnection(connection);
        while (resultSet.next()) {
            deliveries.add(new Delivery(
                    resultSet.getInt("id"),
                    resultSet.getObject("pickupTime", LocalDateTime.class),
                    resultSet.getObject("deliveryTime", LocalDateTime.class),
                    resultSet.getString("deliveryStatus"),
                    resultSet.getDouble("distanceKm"),
                    resultSet.getInt("couriersId"),
                    resultSet.getInt("ordersId")
            ));
        }

        return deliveries;
    }

    @Override
    public void save(Delivery entity) throws SQLException, IOException {
        String sql = "INSERT INTO deliveries(pickupTime, deliveryTime, deliveryStatus, " +
                "distanceKm, couriersId, ordersId) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, entity.pickupTime());
        statement.setObject(2, entity.deliveryTime());
        statement.setObject(3, entity.deliveryStatus());
        statement.setDouble(4, entity.distanceKm());
        statement.setInt(5, entity.courierId());
        statement.setInt(6, entity.orderId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void update(Delivery entity) throws SQLException, IOException {
        String sql = "UPDATE deliveries SET pickupTime = ?, deliveryTime = ?, deliveryStatus = ?, " +
                "distanceKm = ?, couriersId = ?, ordersId = ? WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, entity.pickupTime());
        statement.setObject(2, entity.deliveryTime());
        statement.setString(3, entity.deliveryStatus());
        statement.setDouble(4, entity.distanceKm());
        statement.setInt(5, entity.courierId());
        statement.setInt(6, entity.orderId());
        statement.setInt(7, entity.id());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteById(int id) throws SQLException, IOException {
        String sql = "DELETE FROM deliveries WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
