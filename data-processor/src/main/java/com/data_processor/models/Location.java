package com.data_processor.models;

import java.util.Objects;

public class Location {

    private String id;
    private String name;
    private double width;
    private double length;

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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", length=" + length +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Location location = (Location) object;

        return Double.compare(location.width, width) == 0 && Double.compare(location.length, length) == 0 &&
                id.equals(location.id) && name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, width, length);
    }
}
