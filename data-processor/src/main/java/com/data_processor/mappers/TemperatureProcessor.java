package com.data_processor.mappers;

import com.data_processor.enums.DeviceType;
import com.data_processor.models.DeviceEvent;

public class TemperatureProcessor {

    public DeviceEvent process(final DeviceEvent deviceEvent) {
        if (deviceEvent.device.type.equals(DeviceType.TEMPERATURE.toString())) {
            this.mapTemperatureValueByUnit(deviceEvent);
        }

        return deviceEvent;
    }

    private DeviceEvent mapTemperatureValueByUnit(final DeviceEvent deviceEvent) {
        if (deviceEvent.device.unit.equals("K")) {
            deviceEvent.value = this.mapKelvinToCelsius(deviceEvent.value);
        }

        if (deviceEvent.device.unit.equals("F")) {
            deviceEvent.value = this.mapFahrenheitToCelsius(deviceEvent.value);
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
