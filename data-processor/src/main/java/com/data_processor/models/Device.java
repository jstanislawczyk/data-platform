package com.data_processor.models;

import java.util.Objects;

public class Device {

    private String id;
    private String name;
    private String type;
    private String unit;
    private String macAddress;
    private double widthCoordinate;
    private double lengthCoordinate;
    private Location location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public double getWidthCoordinate() {
        return widthCoordinate;
    }

    public void setWidthCoordinate(double widthCoordinate) {
        this.widthCoordinate = widthCoordinate;
    }

    public double getLengthCoordinate() {
        return lengthCoordinate;
    }

    public void setLengthCoordinate(double lengthCoordinate) {
        this.lengthCoordinate = lengthCoordinate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", unit='" + unit + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", widthCoordinate=" + widthCoordinate +
                ", lengthCoordinate=" + lengthCoordinate +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Device device = (Device) object;

        return Double.compare(device.widthCoordinate, widthCoordinate) == 0 && Double.compare(device.lengthCoordinate, lengthCoordinate) == 0
                && id.equals(device.id) && name.equals(device.name) && type.equals(device.type) && unit.equals(device.unit)
                && macAddress.equals(device.macAddress) && location.equals(device.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, unit, macAddress, widthCoordinate, lengthCoordinate, location);
    }
}
