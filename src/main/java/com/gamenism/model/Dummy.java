package com.gamenism.model;

import com.gamenism.dao.ActiveRecord;

import javax.persistence.*;

/**
 * User: halil
 * Date: 9/18/13
 * Time: 9:36 PM
 */
@Entity
public class Dummy extends ActiveRecord<Dummy> {

    private Integer yearOfManufacture;
    private String name;
    private Manufacturer manufacturer;

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dummy{");
        sb.append(", ").append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
