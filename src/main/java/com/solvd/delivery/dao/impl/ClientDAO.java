package com.solvd.delivery.dao.impl;

import com.solvd.delivery.dao.interfaces.IClientDAO;
import com.solvd.delivery.model.Client;
import com.solvd.delivery.util.ConnectionPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IClientDAO {
    @Override
    public Client getById(int id) throws SQLException, IOException {
        Client selectedClient = null;

        String sql = "SELECT * FROM clients WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        ConnectionPool.getInstance().releaseConnection(connection);

        while (resultSet.next()) {
            selectedClient = new Client(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("lastName"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("address")
            );
        }

        return selectedClient;
    }

    @Override
    public List<Client> getAll() throws SQLException, IOException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";
        Connection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ConnectionPool.getInstance().releaseConnection(connection);
        while (resultSet.next()) {
            clients.add(new Client(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("lastName"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("address")
            ));
        }

        return clients;
    }

    @Override
    public void save(Client entity) throws SQLException, IOException {
        String sql = "INSERT INTO clients(id, name, lastName, email, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, entity.id());
        statement.setString(2, entity.name());
        statement.setString(3, entity.lastName());
        statement.setString(4, entity.email());
        statement.setString(5, entity.phone());
        statement.setString(6, entity.address());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void update(Client entity) throws SQLException, IOException {
        String sql = "UPDATE clients SET name = ?, lastName = ?, email = ?, phone = ?, address = ?" +
                "WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, entity.name());
        statement.setString(2, entity.lastName());
        statement.setString(3, entity.email());
        statement.setString(4, entity.phone());
        statement.setString(5, entity.address());
        statement.setInt(6, entity.id());
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }

    @Override
    public void deleteById(int id) throws SQLException, IOException {
        String sql = "DELETE FROM clients WHERE id = ?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}