package com.solvd.delivery.service.impl;

import com.solvd.delivery.dao.impl.OrderDAO;
import com.solvd.delivery.dao.interfaces.IMySQLDAO;
import com.solvd.delivery.model.Order;
import com.solvd.delivery.service.interfaces.IOrderService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderService implements IOrderService {

    private static final IMySQLDAO<Order> orderIMySQLDAO = new OrderDAO();

    @Override
    public Order getById(int id) throws SQLException, IOException {
        return orderIMySQLDAO.getById(id);
    }

    @Override
    public List<Order> getAll() throws SQLException, IOException {
        return orderIMySQLDAO.getAll();
    }

    @Override
    public void save(Order entity) throws SQLException, IOException {
        orderIMySQLDAO.save(entity);
    }

    @Override
    public void update(Order entity) throws SQLException, IOException {
        orderIMySQLDAO.update(entity);
    }

    @Override
    public void deleteById(int id) throws SQLException, IOException {
        orderIMySQLDAO.deleteById(id);
    }
}
