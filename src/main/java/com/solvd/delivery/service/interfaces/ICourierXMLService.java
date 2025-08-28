package com.solvd.delivery.service.interfaces;

import com.solvd.delivery.model.Courier;
import com.solvd.delivery.model.xsdWrappers.Couriers;
import com.solvd.delivery.util.CourierSAXParser;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import java.util.List;

public interface ICourierXMLService {
    List<Courier> loadCouriersFromXml(String xmlPath) throws Exception;
    Couriers loadCouriersFromXml(String xmlPath, String xsdPath) throws JAXBException, SAXException;
}
