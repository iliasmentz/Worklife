import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {User} from "../shared/user/user.model";
import {UserService} from "../shared/user/user.service";

@Injectable()
export class ProfileResolver implements Resolve<Promise<User>> {
  constructor(private userService: UserService) {
  }

  resolve(route: ActivatedRouteSnapshot): Promise<User> {

    let username: string = route.params['username'];
    if (username == null) {
      let myUser: User = JSON.parse(localStorage.getItem('currentUser'));
      username = myUser.username;
    }

    return this.userService.getUserProfile(username);

  }


}
