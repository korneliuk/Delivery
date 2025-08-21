package com.solvd.delivery.dao.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public interface IMySQLDAO<T> {

    Logger LOG = LogManager.getLogger(IMySQLDAO.class);

    T getById(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    void save(final T entity) throws SQLException;

    void update(final T entity) throws SQLException;

    void deleteById(int id) throws SQLException;
}
