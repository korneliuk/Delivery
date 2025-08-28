package com.solvd.delivery.dao.impl;

import com.solvd.delivery.dao.interfaces.IRestaurantDAO;
import com.solvd.delivery.model.Restaurant;
import com.solvd.delivery.util.ConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAO implements IRestaurantDAO {
    @Override
    public Restaurant getById(int id) throws SQLException, IOException {
        Restaurant selectedRestaurant = null;

        String sql = "SELECT * FROM restaurants WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        ConnectionPool.getInstance().releaseConnection(connection);
        while (resultSet.next()) {
            selectedRestaurant = new Restaurant(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("phone")
            );
        }

        return selectedRestaurant;
    }

    @Override
    public List<Restaurant> getAll() throws SQLException, IOException {
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurants";
        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ConnectionPool.getInstance().releaseConnection(connection);
        while (resultSet.next()) {
            restaurants.add(new Restaurant(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("phone")
            ));
        }

        return restaurants;
    }

    @Override
    public void save(Restaurant entity) throws SQLException, IOException {
        String sql = "INSERT INTO restaurants(name, address, phone) VALUES (?, ?, ?)";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, entity.name());
        statement.setString(2, entity.address());
        statement.setObject(3, entity.phone());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void update(Restaurant entity) throws SQLException, IOException {
        String sql = "UPDATE restaurants SET name = ?, address = ?, phone = ? WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, entity.name());
        statement.setString(2, entity.address());
        statement.setObject(3, entity.phone());
        statement.setInt(4, entity.id());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteById(int id) throws SQLException, IOException {
        String sql = "DELETE FROM restaurants WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
