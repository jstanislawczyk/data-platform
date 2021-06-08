package com.data_processor.processor;

import com.data_processor.enums.DeviceType;
import com.data_processor.models.DeviceEvent;

public class PressureProcessor {

    public DeviceEvent process(final DeviceEvent deviceEvent) {
        if (deviceEvent.getDevice().getType().equals(DeviceType.PRESSURE.toString())) {
            this.mapPressureValueByUnit(deviceEvent);
        }

        return deviceEvent;
    }

    private DeviceEvent mapPressureValueByUnit(final DeviceEvent deviceEvent) {
        deviceEvent.setReadingUnit("hPa");

        if (deviceEvent.getDevice().getUnit().equals("atm")) {
            deviceEvent.setValue(this.mapKelvinToHectoPascal(deviceEvent.getValue()));
        }

        return deviceEvent;
    }

    private String mapKelvinToHectoPascal(final String value) {
        final double valueAsDouble = Double.parseDouble(value);
        final double celsius = valueAsDouble * 1013.25;

        return Double.toString(celsius);
    }
}
