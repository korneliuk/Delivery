package com.solvd.delivery.dao.impl;

import com.solvd.delivery.dao.interfaces.IMenuDAO;
import com.solvd.delivery.model.Menu;
import com.solvd.delivery.util.ConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO implements IMenuDAO {
    @Override
    public Menu getById(int id) throws SQLException, IOException {
        Menu selectedMenu = null;

        String sql = "SELECT * FROM menus WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        ConnectionPool.getInstance().releaseConnection(connection);
        while (resultSet.next()) {
            selectedMenu = new Menu(
                    resultSet.getInt("id"),
                    resultSet.getInt("restaurantsId")
            );
        }

        return selectedMenu;
    }

    @Override
    public List<Menu> getAll() throws SQLException, IOException {
        List<Menu> menus = new ArrayList<>();
        String sql = "SELECT * FROM menus";
        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ConnectionPool.getInstance().releaseConnection(connection);
        while (resultSet.next()) {
            menus.add(new Menu(
                    resultSet.getInt("id"),
                    resultSet.getInt("restaurantsId")
            ));
        }

        return menus;
    }

    @Override
    public void save(Menu entity) throws SQLException, IOException {
        String sql = "INSERT INTO menus(restaurantsId) VALUES (?)";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, entity.restaurantId());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void update(Menu entity) throws SQLException, IOException {
        String sql = "UPDATE menus SET restaurantsId = ? WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, entity.restaurantId());
        statement.setInt(2, entity.id());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteById(int id) throws SQLException, IOException {
        String sql = "DELETE FROM menus WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}
