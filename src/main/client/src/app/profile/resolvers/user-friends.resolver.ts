import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {User, Users} from "../../shared/user/user.model";
import {UserService} from "../../shared/user/user.service";

@Injectable()
export class UserFriendsResolver implements Resolve<Promise<Users>> {
  constructor(private userService: UserService) {
  }

  resolve(route: ActivatedRouteSnapshot): Promise<Users> {
    const userId = (route.parent.data.user as User).userId;
    return this.userService.getFriends(userId);
  }

}
