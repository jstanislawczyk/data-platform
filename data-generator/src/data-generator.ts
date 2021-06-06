import * as mqtt from 'mqtt';
import {MqttClient} from 'mqtt';
import {DeviceEvent} from "./device-event";
import {v4 as uuidv4} from 'uuid';
import {devices} from "./devices";
import {DeviceType} from "./device-type";
import {pressureUnits, temperatureUnits} from "./units";

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
        this.mqttClient.on('message', (topic: string, message: Buffer) => {
            console.log(message.toString());
        });
    }

    private onConnect(): void {
        this.mqttClient.on('connect', () => {
            this.mqttClient.subscribe('data-generator', async (error: Error) => {
                if (!error) {
                    while (true) {
                        const deviceEvent: DeviceEvent = {
                            id: uuidv4(),
                            value: '',
                            timestamp: Date.now(),
                            device: devices[~~(Math.random() * devices.length)]
                        };

                        if (deviceEvent.device.type === DeviceType.TEMPERATURE) {
                            deviceEvent.device.unit = temperatureUnits[~~(Math.random() * temperatureUnits.length)];

                            switch (deviceEvent.device.unit) {
                                case 'C':
                                    deviceEvent.value = (Math.random() * (30 - 20) + 20).toFixed(1);
                                    break;
                                case 'K':
                                    deviceEvent.value = (Math.random()*(303 - 293) + 293).toFixed(1);
                                    break;
                            }
                        }

                        if (deviceEvent.device.type === DeviceType.PRESSURE) {
                            deviceEvent.device.unit = pressureUnits[~~(Math.random() * pressureUnits.length)];

                            switch (deviceEvent.device.unit) {
                                case 'hPa':
                                    deviceEvent.value = Math.floor(Math.random()*(1050 - 950) + 950).toString();
                                    break;
                                case 'atm':
                                    deviceEvent.value = (Math.random()*(1.04 - 0.94) + 0.94).toFixed(2);
                                    break;
                            }
                        }

                        if (deviceEvent.device.type === DeviceType.HUMIDITY) {
                            deviceEvent.value = Math.floor(Math.random() * (60 - 10) + 10).toString();
                        }

                        this.mqttClient.publish('data-generator', JSON.stringify(deviceEvent));
                        await this.sleep(500);
                    }
                }
            })
        })
    }

    private async sleep(ms: number): Promise<void> {
        return new Promise(resolve => setTimeout(resolve, ms));
    }
}
