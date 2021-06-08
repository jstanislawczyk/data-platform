import * as mqtt from 'mqtt';
import {MqttClient} from 'mqtt';
import {DeviceEvent} from './device-event';
import {v4} from 'uuid';
import {devices} from './devices';
import {DeviceType} from './device-type';

export class DataGenerator {

    private mqttClient: MqttClient;

    constructor() {
        this.mqttClient = mqtt.connect('mqtt://broker.hivemq.com');
    }

    public init() {
        this.onMessage();
        this.onConnect();
    }

    private onMessage(): void {
        this.mqttClient.on('message', (topic: string, message: Buffer) =>
            console.log(message.toString())
        );
    }

    private onConnect(): void {
        const mqttTopic: string = process.env.MQTT_TOPIC || 'data-generator';

        this.mqttClient.on('connect', () => {
            this.mqttClient.subscribe(mqttTopic, async (error: Error) => {
                if (!error) {
                    while (true) {
                        let deviceEvent: DeviceEvent = {
                            id: v4(),
                            value: '',
                            timestamp: Date.now(),
                            device: devices[~~(Math.random() * devices.length)],
                        };

                        if (deviceEvent.device.type === DeviceType.TEMPERATURE) {
                            deviceEvent = this.randomizeTemperatureDeviceEvent(deviceEvent);
                        }

                        if (deviceEvent.device.type === DeviceType.PRESSURE) {
                            deviceEvent = this.randomizePressureDeviceEvent(deviceEvent);
                        }

                        if (deviceEvent.device.type === DeviceType.HUMIDITY) {
                            deviceEvent = this.randomizeHumidityDeviceEvent(deviceEvent);
                        }

                        this.mqttClient.publish(mqttTopic, JSON.stringify(deviceEvent));
                        await this.sleep(500);
                    }
                }
            })
        })
    }

    private randomizeTemperatureDeviceEvent(deviceEvent: DeviceEvent): DeviceEvent {
        switch (deviceEvent.device.unit) {
            case 'C':
                deviceEvent.value = (Math.random() * (30 - 20) + 20).toFixed(1);
                break;
            case 'K':
                deviceEvent.value = (Math.random() * (303 - 293) + 293).toFixed(1);
                break;
            case 'F':
                deviceEvent.value = (Math.random() * (86 - 68) + 86).toFixed(1);
                break;
        }

        return deviceEvent;
    }

    private randomizePressureDeviceEvent(deviceEvent: DeviceEvent): DeviceEvent {
        switch (deviceEvent.device.unit) {
            case 'hPa':
                deviceEvent.value = Math.floor(Math.random() * (1050 - 950) + 950).toString();
                break;
            case 'atm':
                deviceEvent.value = (Math.random() * (1.04 - 0.94) + 0.94).toFixed(2);
                break;
        }

        return deviceEvent;
    }

    private randomizeHumidityDeviceEvent(deviceEvent: DeviceEvent): DeviceEvent {
        deviceEvent.value = Math.floor(Math.random() * (40 - 10) + 10).toString();

        return deviceEvent;
    }

    private async sleep(ms: number): Promise<void> {
        return new Promise(resolve => setTimeout(resolve, ms));
    }
}
