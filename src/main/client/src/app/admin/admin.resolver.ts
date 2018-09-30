import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {Users} from "../shared/user/user.model";
import {UserService} from "../shared/user/user.service";

@Injectable()
export class AdminResolver implements Resolve<Promise<Users>> {

  constructor(private userService: UserService) {
  }

  resolve(route: ActivatedRouteSnapshot): Promise<Users> {
    return this.userService.getAllUsers();
  }
}
