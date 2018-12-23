export class ConnectionStatus {
  connectionStatus: number;

  constructor(obj: ConnectionStatus) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}
