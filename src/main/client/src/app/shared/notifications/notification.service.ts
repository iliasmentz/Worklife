import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";
import {map} from "rxjs/operators";
import {Notification, Notifications} from "./notification.model";

@Injectable()
export class NotificationService {

  constructor(private _repoService: RepoService) {
  }

  getNotification(): Promise<Notifications> {
    return this._repoService.get('notifications/')
      .pipe(map((notifications: any[]) => {
        return notifications.map(notification => this.deserializeNotification(notification))
      }))
      .toPromise() as Promise<Notifications>;
  }

  seen(notificationId: number) {
    return this._repoService.post('notifications/' + notificationId)
      .subscribe(() => {
      }, error => console.log(error));
  }

  private deserializeNotification(resp): Notification {
    return new Notification(resp);
  }

}
