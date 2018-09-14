import {map} from "rxjs/operators";
import {RepoService} from "../../shared/repo/repo.service";
import {Experience, Experiences} from "../../shared/experience/experience.model";
import {Injectable} from "@angular/core";

@Injectable()
export class ExperienceService {
  constructor(private repoService: RepoService) { }

  getExperience(userId: number): Promise<Experiences> {
    return this.repoService.get('profile/experience/'+userId)
      .pipe(map((experiences: any[]) => {
        return experiences.map(experience => this.deserializeExperience(experience))
      }))
      .toPromise() as Promise<Experiences>;
  }

  private deserializeExperience(resp): Experience {
    return new Experience(resp);
  }
}
