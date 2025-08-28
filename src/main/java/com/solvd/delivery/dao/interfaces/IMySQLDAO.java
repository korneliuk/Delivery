package com.solvd.delivery.dao.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IMySQLDAO<T> {

    Logger LOG = LogManager.getLogger(IMySQLDAO.class);

    T getById(int id) throws SQLException, IOException;

    List<T> getAll() throws SQLException, IOException;

    void save(final T entity) throws SQLException, IOException;

    void update(final T entity) throws SQLException, IOException;

    void deleteById(int id) throws SQLException, IOException;
}
