package com.solvd.delivery.util.jaxbReaders.interfaces;

import com.solvd.delivery.model.xsdWrappers.Couriers;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

public interface IJAXCouriersReader {
    Couriers unmarshal(String xmlPath, String xsdPath) throws JAXBException, SAXException;
}
