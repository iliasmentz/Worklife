import {Injectable} from "@angular/core";
import {RepoService} from "../../shared/repo/repo.service";
import {Skill, Skills} from "../../shared/skills/skill.model";
import {map} from "rxjs/operators";

@Injectable()
export class SkillService {
  constructor(private repoService: RepoService) { }

  getSkill(userId: number): Promise<Skills> {
    return this.repoService.get('profile/skills/'+userId)
      .pipe(map((skills: any[]) => {
        return skills.map(skill => this.deserializeSkill(skill))
      }))
      .toPromise() as Promise<Skills>;
  }

  private deserializeSkill(resp): Skill {
    return new Skill(resp);
  }
}
