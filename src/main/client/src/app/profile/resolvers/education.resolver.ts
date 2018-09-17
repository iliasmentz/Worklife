import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {Educations} from "../../shared/education/education.model";
import {EducationService} from "../education/education.service";
import {User} from "../../shared/user/user.model";

@Injectable()
export class EducationResolver implements Resolve<Promise<Educations>> {

  constructor(private educationService: EducationService) {
  }

  resolve(route: ActivatedRouteSnapshot): Promise<Educations> {
    const userId = (route.parent.data.user as User).userId;
    return this.educationService.getEducation(userId);
  }
}
