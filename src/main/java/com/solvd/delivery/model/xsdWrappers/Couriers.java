package com.solvd.delivery.model.xsdWrappers;

import com.solvd.delivery.model.Courier;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "couriers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Couriers {

    @XmlElement(name = "courier")
    private List<Courier> couriers;

    public List<Courier> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<Courier> couriers) {
        this.couriers = couriers;
    }
}
