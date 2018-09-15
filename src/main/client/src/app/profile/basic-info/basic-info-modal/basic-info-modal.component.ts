import {Component, OnInit} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap';

@Component({
  selector: 'app-basic-info-modal',
  templateUrl: './basic-info-modal.component.html'
})
export class BasicInfoModalComponent implements OnInit {

  title: string;

  constructor(private _modal: BsModalRef) {
  }

  close() {
    if (this._modal) {
      this._modal.hide();
      this._modal = null;
    }
  }

  ngOnInit() {
  }

}
