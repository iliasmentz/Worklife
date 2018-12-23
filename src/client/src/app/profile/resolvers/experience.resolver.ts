import {Injectable} from "@angular/core";
import {Experiences} from "../../shared/experience/experience.model";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {ExperienceService} from "../experience/experience.service";
import {User} from "../../shared/user/user.model";

@Injectable()
export class ExperienceResolver implements Resolve<Promise<Experiences>> {

  constructor(private experienceService: ExperienceService) {
  }

  resolve(route: ActivatedRouteSnapshot): Promise<Experiences> {
    const userId = (route.parent.data.user as User).userId;
    return this.experienceService.getExperience(userId);
  }


}
