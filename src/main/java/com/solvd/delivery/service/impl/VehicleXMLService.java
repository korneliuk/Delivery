package com.solvd.delivery.service.impl;

import com.solvd.delivery.model.Vehicle;
import com.solvd.delivery.service.interfaces.IVehicleXMLService;
import com.solvd.delivery.util.VehicleSAXParser;

import java.util.List;

public class VehicleXMLService implements IVehicleXMLService {
    private static final VehicleSAXParser parser = new VehicleSAXParser();

    @Override
    public List<Vehicle> loadVehiclesFromXml(String path) throws Exception {
        return parser.parse(path);
    }
}
