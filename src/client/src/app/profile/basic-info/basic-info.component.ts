import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {User} from "../../shared/user/user.model";
import {BsModalService, ModalOptions} from 'ngx-bootstrap/modal';
import {BasicInfoModalComponent} from './basic-info-modal/basic-info-modal.component';
import {ActivatedRoute} from "@angular/router";


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

  constructor(private route: ActivatedRoute,
              private _modal: BsModalService) {
  }

  ngOnInit() {
  }

  myProfile(): boolean {
    let myUser = JSON.parse(localStorage.getItem('currentUser'));
    return myUser.userId === this.user.userId;
  }

  openEditModal() {
    const initialState = {
      user: this.user
    };

    this._modal.show(BasicInfoModalComponent, {...options, initialState});
  }

  ngOnDestroy(): void {
  }


}
