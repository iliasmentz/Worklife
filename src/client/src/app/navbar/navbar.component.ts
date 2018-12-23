import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../shared/user/user.model";
import {AuthService} from "../shared/auth/auth.service";
import {Notification, Notifications} from "../shared/notifications/notification.model";
import {NotificationService} from "../shared/notifications/notification.service";
import {interval} from "rxjs";
import {FileUploadService} from "../shared/fiile-upload/file-upload.service";
import {BsModalService, ModalOptions} from "ngx-bootstrap";
import {SearchUserComponent} from "./search-user/search-user.component";

const options: ModalOptions = {
  class: 'modal-m',
  backdrop: 'static',
};

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit, OnDestroy {
  user: User;
  username: string;
  notifications = [];
  intervallTimer = interval(5000);
  public timeObservable: any;


  constructor(private router: Router,
              private _uploadService: FileUploadService,
              private _modal: BsModalService,
              private _notificationService: NotificationService,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.username = this.user.username;

    this._uploadService.imagePath.subscribe((newImagePath: string) => {
      this.user.imagePath = newImagePath
    });

    this.getNotifications();
    this.timeObservable = this.intervallTimer
      .subscribe(() => {
        this.getNotifications();
      });
  }

  logout() {
    this.router.navigate(['/welcome']);
    this.authService.logout();

  }

  ngOnDestroy(): void {
    this.timeObservable.unsubscribe();
  }

  private getNotifications() {
    this._notificationService.getNotification()
      .then((data: Notifications) => {
        this.notifications = data;
      });
  }

  markAsSeen(notification: Notification) {
    if (notification.type < 2) {
      this.router.navigate(['/profile', this.username]);
    } else {
      this.router.navigate(['/connections', this.user.userId]);
    }
    this._notificationService.seen(notification.notificationId);
    this.notifications = this.notifications.filter(function (obj) {
      return obj.notificationId !== notification.notificationId;
    });
  }

  search() {
    const initialState = {
    };

    this._modal.show(SearchUserComponent, {...options, initialState});
  }
}
