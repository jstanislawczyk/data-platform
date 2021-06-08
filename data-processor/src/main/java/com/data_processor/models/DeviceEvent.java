package com.data_processor.models;

import java.util.Objects;

public class DeviceEvent {

    private String id;
    private String value;
    private String readingUnit;
    private long timestamp;
    private Device device;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getReadingUnit() {
        return readingUnit;
    }

    public void setReadingUnit(String readingUnit) {
        this.readingUnit = readingUnit;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "DeviceEvent{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", readingUnit='" + readingUnit + '\'' +
                ", timestamp=" + timestamp +
                ", device=" + device +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceEvent that = (DeviceEvent) o;
        return timestamp == that.timestamp && id.equals(that.id) && value.equals(that.value) &&
                Objects.equals(readingUnit, that.readingUnit) && device.equals(that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, readingUnit, timestamp, device);
    }
}
