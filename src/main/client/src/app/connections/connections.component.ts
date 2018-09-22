import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {ConnectionService} from "../shared/connections/connection.service";
import {Connection, Connections} from "../shared/connections/connection.model";
import {ConnectionRequests} from "../shared/connection-request/connection-request.model";

@Component({
  selector: 'app-connections',
  templateUrl: './connections.component.html',
  styleUrls: ['./connections.component.css']
})
export class ConnectionsComponent implements OnInit {
  userId: number;
  connections: Connections;
  requests: ConnectionRequests;

  constructor(private route: ActivatedRoute,
              private connectionService: ConnectionService) {
  }

  myProfile(): boolean {
    let user = JSON.parse(localStorage.getItem('currentUser'));
    return user.userId === this.userId;
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.userId = +params['id'];
    });
    this.loadConnections();
  }

  loadConnections() {
    this.connectionService.getUsersConnections(this.userId)
      .then((userConnections: Connections) => {
        this.connections = userConnections;
      })
  }

  deleteConnection(connectionId: number) {
    this.connectionService.deleteConnection(connectionId);
    this.connections = this.connections.filter(function (obj) {
      return obj.connectionId !== connectionId;
    });
  }

  loadRequests() {
    this.connectionService.getUsersRequests()
      .then((requestConnections: ConnectionRequests) => {
        this.requests = requestConnections;
      })
  }

  acceptConnection(connectionRequestId: number) {
    this.connectionService.accept(this.userId, connectionRequestId)
      .then((newConnection: Connection) =>{
        this.connections.push(newConnection);
        this.removeRequest(connectionRequestId);
      })
  }

  rejectConnection(connectionRequestId: number) {
    this.connectionService.reject(this.userId, connectionRequestId)
      .then(() => {
        this.removeRequest(connectionRequestId);
      });
  }

  removeRequest(requestId: number) {
    this.requests = this.requests.filter(function (obj) {
      return obj.connectionRequestId !== requestId;
    });
  }
}
