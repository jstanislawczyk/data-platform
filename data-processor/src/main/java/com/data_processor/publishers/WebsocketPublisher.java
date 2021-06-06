package com.data_processor.publishers;

import com.data_processor.clients.WebsocketClient;
import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

public class WebsocketPublisher {

    private WebSocketClient webSocketClient;

    public WebsocketPublisher() throws URISyntaxException, InterruptedException {
        final String websocketUrl = Optional
                .ofNullable(System.getenv("WEBSOCKET_URL"))
                .orElse("ws://localhost:3000");

        this.webSocketClient = new WebsocketClient(new URI(websocketUrl));
        this.webSocketClient.connectBlocking();
    }

    public void publish(final String message) {
        this.webSocketClient.send(message);
    }
}
