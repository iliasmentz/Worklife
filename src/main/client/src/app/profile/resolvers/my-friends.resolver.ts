import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {Connections} from "../../shared/connections/connection.model";
import {ConnectionService} from "../../shared/connections/connection.service";

@Injectable()
export class MyFriendsResolver implements Resolve<Promise<Connections>> {
  constructor(private connectionService: ConnectionService) {
  }

  resolve(route: ActivatedRouteSnapshot): Promise<Connections> {

    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return this.connectionService.getUsersConnections(currentUser.userId);
  }

}
