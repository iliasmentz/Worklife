import {Injectable} from "@angular/core";
import {RepoService} from "../../shared/repo/repo.service";
import {Education, Educations} from "../../shared/education/education.model";
import {map} from 'rxjs/operators';
import {EducationDto} from "../../shared/education/education-dto.model";
import {Subject} from "rxjs";

@Injectable()
export class EducationService {
  education = new Subject();

  constructor(private repoService: RepoService) { }

  getEducation(userId: number): Promise<Educations> {
    return this.repoService.get('profile/education/'+userId)
      .pipe(map((educations: any[]) => {
        return educations.map(education => this.deserializeEducation(education))
      }))
      .toPromise() as Promise<Educations>;
  }


  updateEducation(educationId: number, educationRequest: EducationDto) {
    return this.repoService.put("profile/education/" + educationId, educationRequest)
      .pipe(map(education => this.deserializeEducation(education)))
      .toPromise() as Promise<Education>;
  }

  addEducation(educationRequest: EducationDto) {
    return this.repoService.post("profile/education/", educationRequest)
      .pipe(map(education => this.deserializeEducation(education)))
      .toPromise() as Promise<Education>;
  }

  deleteEducation(educationId: number) {
    this.repoService.delete("profile/education/" + educationId)
      .subscribe(() => {
      }, error => console.log(error));
  }

  private deserializeEducation(resp): Education {
    return new Education(resp);
  }
}
