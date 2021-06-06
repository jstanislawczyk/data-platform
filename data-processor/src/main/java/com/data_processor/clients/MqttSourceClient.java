package com.data_processor.clients;

import org.apache.flink.api.common.functions.StoppableFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.eclipse.paho.client.mqttv3.*;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class MqttSourceClient implements SourceFunction<String>, StoppableFunction {

    private transient MqttClient client;
    private transient volatile boolean running;
    private transient Object waitLock;

    public MqttSourceClient() {
    }

    @Override
    public void stop() {
        close();
    }

    @Override
    public void run(final SourceContext<String> ctx) throws Exception {
        final String mqttHost = Optional
                .ofNullable(System.getenv("MQTT_HOST"))
                .orElse("tcp://broker.hivemq.com:1883");
        final String mqttTopic = Optional
                .ofNullable(System.getenv("MQTT_TOPIC"))
                .orElse("data-generator");
        final String clientId = Optional
                .ofNullable(System.getenv("MQTT_CLIENT_ID"))
                .orElse("DataProcessor");
        final int qos = 1;

        final MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);
        connectOptions.setAutomaticReconnect(true);

        client = new MqttClient(mqttHost, clientId);
        client.connect(connectOptions);
        client.subscribe(mqttTopic, qos, (topic, message) -> {
            String msg = new String(message.getPayload(), StandardCharsets.UTF_8);
            ctx.collect(msg);
        });

        running = true;
        waitLock = new Object();

        while (running) {
            synchronized (waitLock) {
                waitLock.wait(100L);
            }
        }
    }

    @Override
    public void cancel() {
        close();
    }

    private void close() {
        try {
            if (client != null) {
                client.disconnect();
            }
        } catch (MqttException exception) {
            System.out.println("[MQTT] Connection exception");
            exception.printStackTrace();
        } finally {
            this.running = false;
        }

        synchronized (waitLock) {
            waitLock.notify();
        }
    }
}
