package com.solvd.delivery.service.impl;

import com.solvd.delivery.model.Courier;
import com.solvd.delivery.service.interfaces.ICourierXMLService;
import com.solvd.delivery.util.CourierSAXParser;

import java.util.List;

public class CourierXMLService implements ICourierXMLService {
    private final static CourierSAXParser parser = new CourierSAXParser();

    @Override
    public List<Courier> loadCouriersFromXml(String path) throws Exception {
        return parser.parse(path);
    }
}
