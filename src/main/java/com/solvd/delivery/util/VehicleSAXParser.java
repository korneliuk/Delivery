package com.solvd.delivery.util;

import com.solvd.delivery.model.Vehicle;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VehicleSAXParser extends DefaultHandler {
    private final List<Vehicle> PARSED_VEHICLES = new ArrayList<>();
    private Vehicle parsedVehicle;
    private final StringBuilder DATA = new StringBuilder();

    public List<Vehicle> parse(String filePath) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(new File(filePath), this);

        return PARSED_VEHICLES;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("vehicle")) {
            parsedVehicle = new Vehicle();
        }
        DATA.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        DATA.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "id" -> parsedVehicle.setId(Integer.parseInt(DATA.toString()));
            case "type" -> parsedVehicle.setType(DATA.toString());
            case "plateNumber" -> parsedVehicle.setPlateNumber(DATA.toString());
            case "couriersId" -> parsedVehicle.setCourierId(Integer.parseInt(DATA.toString()));
            case "vehicle" -> PARSED_VEHICLES.add(parsedVehicle);
        }
    }
}
