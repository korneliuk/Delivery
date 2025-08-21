package com.solvd.delivery.dao.impl;

import com.solvd.delivery.dao.interfaces.IMySQLDAO;
import com.solvd.delivery.model.Rating;
import com.solvd.delivery.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO implements IMySQLDAO<Rating> {
    @Override
    public Rating getById(int id) throws SQLException {
        Rating selectedRating = null;

        String sql = "SELECT * FROM ratings WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        ConnectionPool.releaseConnection(connection);
        while (resultSet.next()) {
            selectedRating = new Rating(
                    resultSet.getInt("id"),
                    resultSet.getInt("score"),
                    resultSet.getString("comment"),
                    resultSet.getInt("clientsId"),
                    resultSet.getInt("couriersId"),
                    resultSet.getInt("ordersId")
            );
        }

        return selectedRating;
    }

    @Override
    public List<Rating> getAll() throws SQLException {
        List<Rating> ratings = new ArrayList<>();
        String sql = "SELECT * FROM ratings";
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ConnectionPool.releaseConnection(connection);
        while (resultSet.next()) {
            ratings.add(new Rating(
                    resultSet.getInt("id"),
                    resultSet.getInt("score"),
                    resultSet.getString("comment"),
                    resultSet.getInt("clientsId"),
                    resultSet.getInt("couriersId"),
                    resultSet.getInt("ordersId")
            ));
        }

        return ratings;
    }

    @Override
    public void save(Rating entity) throws SQLException {
        String sql = "INSERT INTO ratings(score, comment, clientsId, couriersId, ordersId) VALUES (?, ?, ?, ?, ?)";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, entity.score());
        statement.setString(2, entity.comment());
        statement.setInt(3, entity.clientId());
        statement.setInt(4, entity.courierId());
        statement.setInt(5, entity.orderId());
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }

    @Override
    public void update(Rating entity) throws SQLException {
        String sql = "UPDATE ratings SET score = ?, comment = ?, clientsId = ?," +
                " couriersId = ?, ordersId = ? WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, entity.score());
        statement.setString(2, entity.comment());
        statement.setInt(3, entity.clientId());
        statement.setInt(4, entity.courierId());
        statement.setInt(5, entity.orderId());
        statement.setInt(6, entity.id());
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }

    @Override
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM ratings WHERE id = ?";
        Connection connection = ConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPool.releaseConnection(connection);
    }
}
