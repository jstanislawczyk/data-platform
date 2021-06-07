export class Message {

  public readonly messageType: string;
  public readonly timestamp: number;
  public readonly payload: string;

  constructor(messageType: string, payload: string) {
    this.messageType = messageType;
    this.payload = payload;
    this.timestamp = Date.now();
  }
}
