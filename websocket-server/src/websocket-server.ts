import WebSocket, {Server} from 'ws';
import {Message} from './models/message';
import {MessageType} from './enum/message-type';

export class WebsocketServer {

    private port: number = 3000;

    public start(): void {
        console.log(`Starting server on port: ${this.port}`);

        const server: Server = new Server({
            port: this.port,
        });

        this.initConnection(server);
    }

    private initConnection(server: Server): void {
        server.on('connection', (websocket: WebSocket) => {
            console.log('New connection established');

            const newConnectionMessage: Message = {
                timestamp: Date.now(),
                messageType: MessageType.INFO,
                payload: 'Websocket connection established',
            };

            websocket.send(JSON.stringify(newConnectionMessage));
            websocket.on('message', (incomingMessage: any) => {
                console.log(`Receiving new message: ${incomingMessage}`);

                const message: Message = {
                    timestamp: Date.now(),
                    messageType: MessageType.EVENT,
                    payload: incomingMessage,
                };

                console.log(`Sending new message: ${JSON.stringify(message)}`);

                server.clients.forEach((client: WebSocket) => {
                    if (client !== websocket && client.readyState === WebSocket.OPEN) {
                        client.send(JSON.stringify(message));
                    }
                });
            });
        });
    }
}
