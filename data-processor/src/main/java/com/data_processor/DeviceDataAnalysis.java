package com.data_processor;

import com.data_processor.clients.MqttSourceClient;
import com.data_processor.processor.HumidityProcessor;
import com.data_processor.processor.PressureProcessor;
import com.data_processor.processor.TemperatureProcessor;
import com.data_processor.models.DeviceEvent;
import com.data_processor.publishers.WebsocketPublisher;
import com.google.gson.Gson;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

public class DeviceDataAnalysis {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        final MqttSourceClient mqttSourceClient = new MqttSourceClient();
        final DataStreamSource<String> dataSource = env.addSource(mqttSourceClient);
        final DataStream<String> stream = dataSource.map((MapFunction<String, String>) data -> data);

        stream
            .map(event -> {
                final Gson gson = new Gson();
                return gson.fromJson(event, DeviceEvent.class);
            })
            .map(deviceEvent -> {
                System.out.println("[MQTT] " + deviceEvent);
                return deviceEvent;
            })
            .map(deviceEvent -> {
                final TemperatureProcessor temperatureProcessor = new TemperatureProcessor();
                return temperatureProcessor.process(deviceEvent);
            })
            .map(deviceEvent -> {
                final PressureProcessor pressureProcessor = new PressureProcessor();
                return pressureProcessor.process(deviceEvent);
            })
            .map(deviceEvent -> {
                final HumidityProcessor humidityProcessor = new HumidityProcessor();
                return humidityProcessor.process(deviceEvent);
            })
            .addSink((SinkFunction<DeviceEvent>) deviceEvent -> {
                final WebsocketPublisher websocketPublisher = new WebsocketPublisher();
                websocketPublisher.publish(deviceEvent);
            });

        env.execute("Device data analysis");
    }
}
