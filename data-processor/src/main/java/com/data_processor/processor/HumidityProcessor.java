package com.data_processor.processor;

import com.data_processor.enums.DeviceType;
import com.data_processor.models.DeviceEvent;

public class HumidityProcessor {

    public DeviceEvent process(final DeviceEvent deviceEvent) {
        if (deviceEvent.getDevice().getType().equals(DeviceType.HUMIDITY.toString())) {
            deviceEvent.setReadingUnit("%");
        }

        return deviceEvent;
    }
}
