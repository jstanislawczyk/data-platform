package com.data_processor.models;

import java.util.Objects;

public class DeviceEvent {

    public String id;
    public String value;
    public long timestamp;
    public Device device;

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
                ", timestamp=" + timestamp +
                ", device=" + device +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DeviceEvent that = (DeviceEvent) object;
        return timestamp == that.timestamp && id.equals(that.id) && value.equals(that.value) && device.equals(that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, timestamp, device);
    }
}
