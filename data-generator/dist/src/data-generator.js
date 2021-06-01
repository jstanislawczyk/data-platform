"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.DataGenerator = void 0;
const mqtt = require("mqtt");
class DataGenerator {
    constructor() {
        this.mqttClient = mqtt.connect('mqtt://broker.hivemq.com');
    }
    init() {
        this.onMessage();
        this.onConnect();
    }
    onMessage() {
        this.mqttClient.on('message', (topic, message) => {
            console.log(message.toString());
            this.mqttClient.end();
        });
    }
    onConnect() {
        this.mqttClient.on('connect', () => {
            this.mqttClient.subscribe('data-generator', (error) => {
                if (!error) {
                    const device = {
                        a: 1,
                        b: 2,
                        c: 'test3',
                    };
                    this.mqttClient.publish('data-generator', JSON.stringify(device));
                }
            });
        });
    }
}
exports.DataGenerator = DataGenerator;
//# sourceMappingURL=data-generator.js.map