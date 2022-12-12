package com.chuistov.model;

public class Car {
    private final String manufacturer;
    private final String model;
    private final int yearOfManufacture;

    public Car(String manufacturer, String model, int yearOfManufacture) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    @Override
    public String toString() {
        return "Car{" +
               "manufacturer='" + manufacturer + '\'' +
               ", model='" + model + '\'' +
               ", yearOfManufacture=" + yearOfManufacture +
               '}';
    }
}
