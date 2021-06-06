package com.data_processor.publishers;

import com.data_processor.clients.WebsocketClient;
import com.data_processor.models.DeviceEvent;
import com.google.gson.Gson;
import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public class WebsocketPublisher {

    public void publish(final DeviceEvent deviceEvent) throws InterruptedException, URISyntaxException {
        final String websocketUrl = Optional
                .ofNullable(System.getenv("WEBSOCKET_URL"))
                .orElse("ws://localhost:3000");
        final WebSocketClient client = new WebsocketClient(new URI(websocketUrl));
        final Gson gson = new Gson();
        final String message = gson.toJson(deviceEvent);

        client.connectBlocking();
        client.send(message);
        client.close();
    }
}
