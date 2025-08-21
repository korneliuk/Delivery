package com.solvd.delivery.dao.impl;

import com.solvd.delivery.dao.interfaces.IMySQLDAO;
import com.solvd.delivery.model.Dish;
import com.solvd.delivery.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishDAO implements IMySQLDAO<Dish> {
    @Override
    public Dish getById(int id) throws SQLException {
        Dish selectedDish = null;

        String sql = "SELECT * FROM dishes WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        ConnectionPool.releaseConnection(connection);
        while (resultSet.next()) {
            selectedDish = new Dish(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getBigDecimal("price"),
                    resultSet.getInt("menusId")
            );
        }

        return selectedDish;
    }

    @Override
    public List<Dish> getAll() throws SQLException {
        List<Dish> dishes = new ArrayList<>();
        String sql = "SELECT * FROM dishes";
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ConnectionPool.releaseConnection(connection);
        while (resultSet.next()) {
            dishes.add(new Dish(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getBigDecimal("price"),
                    resultSet.getInt("menusId")
            ));
        }

        return dishes;
    }

    @Override
    public void save(Dish entity) throws SQLException {
        String sql = "INSERT INTO dishes(name, description, price, menusId) VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, entity.name());
        statement.setString(2, entity.description());
        statement.setBigDecimal(3, entity.price());
        statement.setInt(4, entity.menuId());
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }

    @Override
    public void update(Dish entity) throws SQLException {
        String sql = "UPDATE dishes SET name = ?, description = ?, price = ?, menusId = ? WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, entity.name());
        statement.setString(2, entity.description());
        statement.setBigDecimal(3, entity.price());
        statement.setInt(4, entity.menuId());
        statement.setInt(5, entity.id());
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM dishes WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }
}