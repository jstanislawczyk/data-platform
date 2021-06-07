package com.data_processor.mappers;

import com.data_processor.enums.DeviceType;
import com.data_processor.models.DeviceEvent;

public class PressureProcessor {

    public DeviceEvent process(final DeviceEvent deviceEvent) {
        deviceEvent.readingUnit = deviceEvent.device.unit;

        if (deviceEvent.device.type.equals(DeviceType.TEMPERATURE.toString())) {
            this.mapPressureValueByUnit(deviceEvent);
        }

        return deviceEvent;
    }

    private DeviceEvent mapPressureValueByUnit(final DeviceEvent deviceEvent) {
        if (deviceEvent.device.unit.equals("atm")) {
            deviceEvent.value = this.mapKelvinToHectoPascal(deviceEvent.value);
        }

        return deviceEvent;
    }

    private String mapKelvinToHectoPascal(final String value) {
        final double valueAsDouble = Double.parseDouble(value);
        final double celsius = valueAsDouble * 1013.25;

        return Double.toString(celsius);
    }
}
