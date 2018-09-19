import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";


@Injectable()
export class FileUploadService {

  constructor(private repoService: RepoService) {
  }

  upload(form: FormData) {
    return this.repoService.post('profile/upload_photo', form)
      .toPromise() as Promise<any>;
  }
}
