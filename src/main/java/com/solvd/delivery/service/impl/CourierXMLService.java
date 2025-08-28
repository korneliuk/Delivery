package com.solvd.delivery.service.impl;

import com.solvd.delivery.model.Courier;
import com.solvd.delivery.model.xsdWrappers.Couriers;
import com.solvd.delivery.service.interfaces.ICourierXMLService;
import com.solvd.delivery.util.CourierSAXParser;
import com.solvd.delivery.util.jaxbReaders.impl.JAXCouriersReader;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import java.util.List;

public class CourierXMLService implements ICourierXMLService {
    private final static CourierSAXParser parser = new CourierSAXParser();

    @Override
    public List<Courier> loadCouriersFromXml(String xmlPath) throws Exception {
        return parser.parse(xmlPath);
    }

    @Override
    public Couriers loadCouriersFromXml(String xmlPath, String xsdPath) throws JAXBException, SAXException {
        return JAXCouriersReader.getInstance().unmarshal(xmlPath, xsdPath);
    }
}
