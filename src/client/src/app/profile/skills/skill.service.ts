import {Injectable} from "@angular/core";
import {RepoService} from "../../shared/repo/repo.service";
import {Skill, Skills} from "../../shared/skills/skill.model";
import {map} from "rxjs/operators";
import {SkillDto} from "../../shared/skills/skill-dto.model";
import {Subject} from "rxjs";

@Injectable()
export class SkillService {
  skill = new Subject();

  constructor(private repoService: RepoService) {
  }

  getSkill(userId: number): Promise<Skills> {
    return this.repoService.get('profile/skills/' + userId)
      .pipe(map((skills: any[]) => {
        return skills.map(skill => this.deserializeSkill(skill))
      }))
      .toPromise() as Promise<Skills>;
  }

  updateSkill(skillId: number, skillRequest: SkillDto) {
    return this.repoService.put("profile/skills/" + skillId, skillRequest)
      .pipe(map(skill => this.deserializeSkill(skill)))
      .toPromise() as Promise<Skill>;
  }

  addSkill(skillRequest: SkillDto) {
    return this.repoService.post("profile/skills/", skillRequest)
      .pipe(map(skill => this.deserializeSkill(skill)))
      .toPromise() as Promise<Skill>;
  }

  deleteSkill(skillId: number) {
    this.repoService.delete("profile/skills/" + skillId)
      .subscribe(()=>{}, error => console.log(error));
  }

  private deserializeSkill(resp): Skill {
    return new Skill(resp);
  }
}
