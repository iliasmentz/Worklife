import {Injectable} from "@angular/core";
import {RepoService} from "../../shared/repo/repo.service";
import {Education, Educations} from "../../shared/education/education.model";
import {map} from 'rxjs/operators';

@Injectable()
export class EducationService {
  constructor(private repoService: RepoService) { }

  getEducation(userId: number): Promise<Educations> {
    return this.repoService.get('profile/education/'+userId)
      .pipe(map((educations: any[]) => {
        return educations.map(education => this.deserializeEducation(education))
      }))
      .toPromise() as Promise<Educations>;
  }

  private deserializeEducation(resp): Education {
    return new Education(resp);
  }
}
