import * as mqtt from 'mqtt';
import {MqttClient} from 'mqtt';

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
            this.mqttClient.end();
        });
    }

    private onConnect(): void {
        this.mqttClient.on('connect', () => {
            this.mqttClient.subscribe('data-generator', (error: Error) => {
                if (!error) {
                    const device: Record<string, any> = {
                        a: 1,
                        b: 2,
                        c: 'test3',
                    };

                    this.mqttClient.publish('data-generator', JSON.stringify(device));
                }
            })
        })
    }
}
