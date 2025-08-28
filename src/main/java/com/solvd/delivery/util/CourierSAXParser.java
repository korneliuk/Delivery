package com.solvd.delivery.util;

import com.solvd.delivery.model.Courier;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CourierSAXParser extends DefaultHandler {
    private final List<Courier> PARSED_COURIERS = new ArrayList<>();
    private Courier courier;
    private final StringBuilder PARSED_DATA = new StringBuilder();

    public List<Courier> parse(String filePath) throws Exception {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(filePath), this);

        return PARSED_COURIERS;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("courier")) {
            courier = new Courier();
        }
        PARSED_DATA.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        PARSED_DATA.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "id" -> courier.setId(Integer.parseInt(PARSED_DATA.toString()));
            case "name" -> courier.setName(PARSED_DATA.toString());
            case "lastName" -> courier.setLastName(PARSED_DATA.toString());
            case "phone" -> courier.setPhone(PARSED_DATA.toString());
            case "vehicleInfo" -> courier.setVehicleInfo(PARSED_DATA.toString());
            case "courier" -> PARSED_COURIERS.add(courier);
        }
    }
}
