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
    private final List<Vehicle> vehicles = new ArrayList<>();
    private Vehicle vehicle;
    private final StringBuilder data = new StringBuilder();

    public List<Vehicle> parse(String filePath) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(new File(filePath), this);

        return vehicles;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("vehicle")) {
            vehicle = new Vehicle();
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
            case "id" -> vehicle.setId(Integer.parseInt(data.toString()));
            case "type" -> vehicle.setType(data.toString());
            case "plateNumber" -> vehicle.setPlateNumber(data.toString());
            case "couriersId" -> vehicle.setCourierId(Integer.parseInt(data.toString()));
            case "vehicle" -> vehicles.add(vehicle);
        }
    }
}
