package com.solvd.delivery.util.jaxbReaders.impl;

import com.solvd.delivery.model.xsdWrappers.Couriers;
import com.solvd.delivery.util.jaxbReaders.interfaces.IJAXCouriersReader;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class JAXCouriersReader implements IJAXCouriersReader {

    private static volatile JAXCouriersReader instance;

    private JAXCouriersReader(){}

    public static JAXCouriersReader getInstance() {
        JAXCouriersReader result = instance;
        if(result != null) {
            return result;
        }
        synchronized (JAXCouriersReader.class) {
            if(instance == null) {
                instance = new JAXCouriersReader();
            }
            return instance;
        }
    }

    @Override
    public Couriers unmarshal(String xmlPath, String xsdPath) throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(Couriers.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(xsdPath));
        unmarshaller.setSchema(schema);

        return (Couriers) unmarshaller.unmarshal(new File(xmlPath));
    }
}
