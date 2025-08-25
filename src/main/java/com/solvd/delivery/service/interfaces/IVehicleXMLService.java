package com.solvd.delivery.service.interfaces;

import com.solvd.delivery.model.Vehicle;

import java.util.List;

public interface IVehicleXMLService {
    List<Vehicle> loadVehiclesFromXml(String path) throws Exception;
}
