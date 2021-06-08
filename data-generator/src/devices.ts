import {Device} from './device';
import {DeviceType} from './device-type';
import {location} from './locations';

export const devices: Device[] = [
    {
        id: '1',
        name: 'Termometr1',
        type: DeviceType.TEMPERATURE,
        unit: 'C',
        macAddress: '00:0A:E6:3E:FD:E1',
        widthCoordinate: 10,
        lengthCoordinate: 10,
        location,
    },
    {
        id: '2',
        name: 'Barometr',
        type: DeviceType.PRESSURE,
        unit: 'hPa',
        macAddress: '00:0A:E5:3A:FB:C3',
        widthCoordinate: 20,
        lengthCoordinate: 10,
        location,
    },
    {
        id: '3',
        name: 'Higrometr',
        type: DeviceType.HUMIDITY,
        unit: '%',
        macAddress: '00:2B:06:4F:AD:E1',
        widthCoordinate: 30,
        lengthCoordinate: 30,
        location,
    },
    {
        id: '4',
        name: 'Termometr2',
        type: DeviceType.TEMPERATURE,
        unit: 'F',
        macAddress: '00:1A:D6:3E:5E:F1',
        widthCoordinate: 10,
        lengthCoordinate: 30,
        location,
    }
];
