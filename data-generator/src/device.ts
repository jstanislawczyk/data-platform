import {Location} from "./location";
import {DeviceType} from "./device-type";

export class Device {
    public id: string;
    public name: string;
    public type: DeviceType;
    public unit: string;
    public macAddress: string;
    public widthCoordinate: number;
    public lengthCoordinate: number;
    public location: Location;
}
