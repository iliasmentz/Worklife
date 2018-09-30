import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {ConnectionService} from "../../shared/connections/connection.service";
import {User} from "../../shared/user/user.model";
import {ConnectionStatus} from "../../shared/connections/connection-status.model";

@Injectable()
export class MyFriendsResolver implements Resolve<Promise<ConnectionStatus>> {
  constructor(private connectionService: ConnectionService) {
  }

  resolve(route: ActivatedRouteSnapshot): Promise<ConnectionStatus> {
    const userId = (route.parent.data.user as User).userId;
    return this.connectionService.getUserStatus(userId);
  }

}
