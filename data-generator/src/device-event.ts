import {Device} from "./device";

export class DeviceEvent {
    public id: string;
    public value: string
    public timestamp: number;
    public device: Device;
}
