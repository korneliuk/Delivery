package com.solvd.delivery.service.impl;

import com.solvd.delivery.dao.impl.ClientDAO;
import com.solvd.delivery.dao.interfaces.IMySQLDAO;
import com.solvd.delivery.model.Client;
import com.solvd.delivery.service.interfaces.IClientService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ClientService implements IClientService {
    private static final IMySQLDAO<Client> clientIMySQLDAO = new ClientDAO();

    @Override
    public Client getById(int id) throws SQLException, IOException {
        return clientIMySQLDAO.getById(id);
    }

    @Override
    public List<Client> getAll() throws SQLException, IOException {
        return clientIMySQLDAO.getAll();
    }

    @Override
    public void save(Client entity) throws SQLException, IOException {
        clientIMySQLDAO.save(entity);
    }

    @Override
    public void update(Client entity) throws SQLException, IOException {
        clientIMySQLDAO.update(entity);
    }

    @Override
    public void deleteById(int id) throws SQLException, IOException {
        clientIMySQLDAO.deleteById(id);
    }
}
