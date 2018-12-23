import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";
import {EmailChange} from "./email-change.model";
import {PasswordChange} from "./password-change.model";

@Injectable()
export class SettingsService {
  constructor(private repoService: RepoService) {
  }

  updateEmail(dto: EmailChange) {
    this.repoService.put("profile/change-email/", dto)
      .subscribe(() => {
      }, error => console.log(error));
  }

  updatePassword(dto: PasswordChange) {
    this.repoService.put("profile/change-pass/", dto)
      .subscribe(() => {
      }, error => console.log(error));
  }
}
