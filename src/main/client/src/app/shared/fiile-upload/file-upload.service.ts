import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";
import {Subject} from "rxjs";


@Injectable()
export class FileUploadService {
  imagePath = new Subject();

  constructor(private repoService: RepoService) {
  }

  upload(form: FormData) {
    return this.repoService.post('profile/upload_photo', form)
      .toPromise() as Promise<any>;
  }
}
