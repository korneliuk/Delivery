package com.solvd.delivery.service.interfaces;

import com.solvd.delivery.model.Courier;
import com.solvd.delivery.util.CourierSAXParser;

import java.util.List;

public interface ICourierXMLService {
    List<Courier> loadCouriersFromXml(String path) throws Exception;
}
