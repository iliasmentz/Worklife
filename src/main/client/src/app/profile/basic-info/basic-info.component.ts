import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {User} from "../../shared/user/user.model";
import {BsModalService, ModalOptions} from 'ngx-bootstrap/modal';
import {BasicInfoModalComponent} from './basic-info-modal/basic-info-modal.component';
import {UserService} from "../../shared/user/user.service";


const options: ModalOptions = {
  class: 'modal-m',
  backdrop: 'static',
};

@Component({
  selector: 'app-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css']
})
export class BasicInfoComponent implements OnInit, OnDestroy {
  @Input() user: User;
  editMode: boolean = false;


  constructor(private _modal: BsModalService,
              private userService: UserService) {
  }

  ngOnInit() {
    if (this.myProfile()) {
      this.userService.user.subscribe(
        (updatedUser: User) => {
          this.user = updatedUser;
        })
    }
  }

  myProfile(): boolean {
    let user = JSON.parse(localStorage.getItem('currentUser'));
    return user.userId === this.user.userId;
  }

  openEditModal() {
    const initialState = {
      user: this.user
    };

    this._modal.show(BasicInfoModalComponent, {...options, initialState});
  }

  ngOnDestroy(): void {
    this.userService.user.unsubscribe();
  }


}
