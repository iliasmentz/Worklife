import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve} from "@angular/router";
import {SkillService} from "../skills/skill.service";
import {Skills} from "../../shared/skills/skill.model";
import {User} from "../../shared/user/user.model";

@Injectable()
export class SkillsResolver implements Resolve<Promise<Skills>> {

  constructor(private skillService: SkillService) {

  }

  resolve(route: ActivatedRouteSnapshot): Promise<Skills> {
    const userId = (route.parent.data.user as User).userId;
    return this.skillService.getSkill(userId);
  }
}
