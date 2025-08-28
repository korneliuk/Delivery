package com.solvd.delivery.service.impl;

import com.solvd.delivery.dao.impl.RatingDAO;
import com.solvd.delivery.dao.interfaces.IMySQLDAO;
import com.solvd.delivery.model.Rating;
import com.solvd.delivery.service.interfaces.IRatingService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RatingService implements IRatingService {

    private static final IMySQLDAO<Rating> ratingIMySQLDAO = new RatingDAO();

    @Override
    public Rating getById(int id) throws SQLException, IOException {
        return ratingIMySQLDAO.getById(id);
    }

    @Override
    public List<Rating> getAll() throws SQLException, IOException {
        return ratingIMySQLDAO.getAll();
    }

    @Override
    public void save(Rating entity) throws SQLException, IOException {
        ratingIMySQLDAO.save(entity);
    }

    @Override
    public void update(Rating entity) throws SQLException, IOException {
        ratingIMySQLDAO.update(entity);
    }

    @Override
    public void deleteById(int id) throws SQLException, IOException {
        ratingIMySQLDAO.deleteById(id);
    }
}
