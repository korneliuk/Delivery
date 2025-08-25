package com.solvd.delivery.util;

import com.solvd.delivery.model.Courier;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CourierSAXParser extends DefaultHandler {
    private List<Courier> couriers = new ArrayList<>();
    private Courier courier;
    private StringBuilder data = new StringBuilder();

    public List<Courier> parse(String filePath) throws Exception {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(filePath), this);

        return couriers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("courier")) {
            courier = new Courier();
        }
        data.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        data.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "id" -> courier.setId(Integer.parseInt(data.toString()));
            case "name" -> courier.setName(data.toString());
            case "lastName" -> courier.setLastName(data.toString());
            case "phone" -> courier.setPhone(data.toString());
            case "vehicleInfo" -> courier.setVehicleInfo(data.toString());
            case "courier" -> couriers.add(courier);
        }
    }
}
