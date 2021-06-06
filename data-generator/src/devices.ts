import {Device} from "./device";
import {DeviceType} from "./device-type";
import {locations} from "./locations";

export const devices: Device[] = [
    {
        id: '1',
        name: 'Termometr1',
        type: DeviceType.TEMPERATURE,
        unit: '',
        macAddress: '00:0A:E6:3E:FD:E1',
        widthCoordinate: 10,
        lengthCoordinate: 10,
        location: locations
    },
    {
        id: '2',
        name: 'Barometr',
        type: DeviceType.PRESSURE,
        unit: '',
        macAddress: '00:0A:E5:3A:FB:C3',
        widthCoordinate: 10,
        lengthCoordinate: 10,
        location: locations
    },
    {
        id: '3',
        name: 'Higrometr',
        type: DeviceType.HUMIDITY,
        unit: '%',
        macAddress: '00:2B:06:4F:AD:E1',
        widthCoordinate: 10,
        lengthCoordinate: 10,
        location: locations
    },
    {
        id: '4',
        name: 'Termometr2',
        type: DeviceType.TEMPERATURE,
        unit: '',
        macAddress: '00:1A:D6:3E:5E:F1',
        widthCoordinate: 10,
        lengthCoordinate: 10,
        location: locations
    }
];

