package com.solvd.delivery.service.impl;

import com.solvd.delivery.dao.impl.PaymentDAO;
import com.solvd.delivery.dao.interfaces.IMySQLDAO;
import com.solvd.delivery.model.Payment;
import com.solvd.delivery.service.interfaces.IPaymentService;

import java.sql.SQLException;
import java.util.List;

public class PaymentService implements IPaymentService {

    private static final IMySQLDAO<Payment> paymentIMySQLDAO = new PaymentDAO();

    @Override
    public Payment getById(int id) throws SQLException {
        return paymentIMySQLDAO.getById(id);
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        return paymentIMySQLDAO.getAll();
    }

    @Override
    public void save(Payment entity) throws SQLException {
        paymentIMySQLDAO.save(entity);
    }

    @Override
    public void update(Payment entity) throws SQLException {
        paymentIMySQLDAO.update(entity);
    }

    @Override
    public void deleteById(int id) throws SQLException {
        paymentIMySQLDAO.deleteById(id);
    }
}
