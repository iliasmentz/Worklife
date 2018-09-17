import {map} from "rxjs/operators";
import {RepoService} from "../../shared/repo/repo.service";
import {Experience, Experiences} from "../../shared/experience/experience.model";
import {Injectable} from "@angular/core";
import {ExperienceDto} from "../../shared/experience/experience-dto.model";
import {Subject} from "rxjs";

@Injectable()
export class ExperienceService {
  experience = new Subject();
  present: boolean;

  constructor(private repoService: RepoService) { }

  getExperience(userId: number): Promise<Experiences> {
    return this.repoService.get('profile/experience/'+userId)
      .pipe(map((experiences: any[]) => {
        return experiences.map(experience => this.deserializeExperience(experience))
      }))
      .toPromise() as Promise<Experiences>;
  }

  updateExperience(experienceId: number, experienceRequest: ExperienceDto) {
    return this.repoService.put("profile/experience/" + experienceId, experienceRequest)
      .pipe(map(experience => this.deserializeExperience(experience)))
      .toPromise() as Promise<Experience>;
  }

  addExperience(experienceRequest: ExperienceDto) {
    return this.repoService.post("profile/experience/", experienceRequest)
      .pipe(map(experience => this.deserializeExperience(experience)))
      .toPromise() as Promise<Experience>;
  }

  deleteExperience(experienceId: number) {
    this.repoService.delete("profile/experience/" + experienceId)
      .subscribe(() => {
      }, error => console.log(error));
  }

  private deserializeExperience(resp): Experience {
    return new Experience(resp);
  }
}
