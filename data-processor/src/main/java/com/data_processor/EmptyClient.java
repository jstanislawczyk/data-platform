package com.data_processor;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.ByteBuffer;

public class EmptyClient extends WebSocketClient {

    public EmptyClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(final ServerHandshake serverHandshake) {
        System.out.println("[Websocket] New connection opened");
    }

    @Override
    public void onClose(final int code, final String reason, final boolean remote) {
        System.out.println("[Websocket] Closed with exit code: " + code + ". Additional info: " + reason);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("[Websocket] Received message: " + message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("[Websocket] Received ByteBuffer");
    }

    @Override
    public void onError(Exception exception) {
        System.err.println("[Websocket] An error occurred: " + exception);
    }
}