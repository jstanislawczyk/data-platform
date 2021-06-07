package com.data_processor.processor;

import com.data_processor.enums.DeviceType;
import com.data_processor.models.DeviceEvent;

public class TemperatureProcessor {

    public DeviceEvent process(final DeviceEvent deviceEvent) {
        if (deviceEvent.getDevice().getType().equals(DeviceType.TEMPERATURE.toString())) {
            this.mapTemperatureValueByUnit(deviceEvent);
        }

        return deviceEvent;
    }

    private DeviceEvent mapTemperatureValueByUnit(final DeviceEvent deviceEvent) {
        deviceEvent.setReadingUnit("C");

        if (deviceEvent.getDevice().getUnit().equals("K")) {
            deviceEvent.setValue(this.mapKelvinToCelsius(deviceEvent.getValue()));
        }

        if (deviceEvent.getDevice().getUnit().equals("F")) {
            deviceEvent.setValue(this.mapFahrenheitToCelsius(deviceEvent.getValue()));
        }

        return deviceEvent;
    }

    private String mapKelvinToCelsius(final String value) {
        final double valueAsDouble = Double.parseDouble(value);
        final double celsius = valueAsDouble - 273.15;

        return Double.toString(celsius);
    }

    private String mapFahrenheitToCelsius(final String value) {
        final double valueAsDouble = Double.parseDouble(value);
        final double celcius = ((valueAsDouble - 32) * 5) / 9;

        return Double.toString(celcius);
    }
}
