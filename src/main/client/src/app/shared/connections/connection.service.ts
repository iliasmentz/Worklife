import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";
import {map} from "rxjs/operators";
import {Connection, Connections} from "./connection.model";
import {ConnectionRequest, ConnectionRequests} from "../connection-request/connection-request.model";

@Injectable()
export class ConnectionService {

  constructor(private _repoService: RepoService) {
  }

  getUsersConnections(userId: number) {
    return this._repoService.get('network/connections/' + userId)
      .pipe(map((connections: any[]) => {
        return connections.map(connection => this.deserializeConnection(userId, connection))
      }))
      .toPromise() as Promise<Connections>;
  }

  deleteConnection(connectionId: number) {
    this._repoService.delete("network/connections/" + connectionId)
      .subscribe(() => {
      }, error => console.log(error));
  }

  getUsersRequests() {
    return this._repoService.get('network/connections/requests/')
      .pipe(map((connections: any[]) => {
        return connections.map(connectionRequest => this.deserializeConnectionRequest(connectionRequest))
      }))
      .toPromise() as Promise<ConnectionRequests>;
  }

  accept(userId: number, connectionRequestId: number) {
    return this._repoService.post('network/connections/requests/respond/' + connectionRequestId + '/accept')
      .pipe(map((connection: any[]) => {
        return this.deserializeConnection(userId, connection);
      }))
      .toPromise() as Promise<Connection>;
  }

  reject(userId: number, connectionRequestId: number) {
    return this._repoService.post('network/connections/requests/respond/' + connectionRequestId + '/reject')
      .pipe(map((response: string) => {
        return response;
      }))
      .toPromise() as Promise<string>;
  }

  newRequest(userId: number) {
    return this._repoService.post('network/connections/request/create' + userId)
      .pipe(map((connectionRequest: any[]) => {
        return this.deserializeConnectionRequest(connectionRequest);
      }))
      .toPromise() as Promise<ConnectionRequest>;
  }


  private deserializeConnection(userId: number, resp): Connection {
    const {userRequested, userAccepted, ...connection} = resp;
    let connectedUser = userAccepted;
    if (userId !== userRequested.userId) {
      connectedUser = userRequested;
    }
    connection.connectedUser = connectedUser;
    return new Connection(connection);
  }

  private deserializeConnectionRequest(resp): ConnectionRequest {
    return new ConnectionRequest(resp);
  }


}
