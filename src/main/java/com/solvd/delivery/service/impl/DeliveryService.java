package com.solvd.delivery.service.impl;

import com.solvd.delivery.dao.impl.DeliveryDAO;
import com.solvd.delivery.dao.interfaces.IMySQLDAO;
import com.solvd.delivery.model.Delivery;
import com.solvd.delivery.service.interfaces.IDeliveryService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DeliveryService implements IDeliveryService {

    private static final IMySQLDAO<Delivery> deliveryIMySQLDAO = new DeliveryDAO();

    @Override
    public Delivery getById(int id) throws SQLException, IOException {
        return deliveryIMySQLDAO.getById(id);
    }

    @Override
    public List<Delivery> getAll() throws SQLException, IOException {
        return deliveryIMySQLDAO.getAll();
    }

    @Override
    public void save(Delivery entity) throws SQLException, IOException {
        deliveryIMySQLDAO.save(entity);
    }

    @Override
    public void update(Delivery entity) throws SQLException, IOException {
        deliveryIMySQLDAO.update(entity);
    }

    @Override
    public void deleteById(int id) throws SQLException, IOException {
        deliveryIMySQLDAO.deleteById(id);
    }
}
