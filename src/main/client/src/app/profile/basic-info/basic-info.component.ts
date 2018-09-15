import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../shared/user/user.model";
import {BsModalService, ModalOptions} from 'ngx-bootstrap/modal';
import {BasicInfoModalComponent} from './basic-info-modal/basic-info-modal.component';

@Component({
  selector: 'app-basic-info',
  templateUrl: './basic-info.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css']
})
export class BasicInfoComponent implements OnInit {

  @Input() user: User;

  constructor(private _modal: BsModalService) {
  }

  ngOnInit() {
  }

  openEditModal() {

    const options: ModalOptions = {
      initialState: {
        title: 'ILIAS',
      }
    };

    this._modal.show(BasicInfoModalComponent, options);
  }
}
