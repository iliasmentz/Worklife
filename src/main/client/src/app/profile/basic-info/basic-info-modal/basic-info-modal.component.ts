import {Component, OnInit} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap';
import {FormBuilder, FormGroup} from "@angular/forms";
import {User} from "../../../shared/user/user.model";
import {UserDto} from "../../../shared/user/user-dto.model";
import {UserService} from "../../../shared/user/user.service";

@Component({
  selector: 'app-basic-info-modal',
  templateUrl: './basic-info-modal.component.html'
})
export class BasicInfoModalComponent implements OnInit {

  title: string;
  user: User;
  basicInfoForm: FormGroup;

  constructor(public _modal: BsModalRef, private _fb: FormBuilder,
              private userService: UserService) {
  }

  close() {
    if (this._modal) {
      this._modal.hide();
      this._modal = null;
    }
  }

  ngOnInit() {
    this.basicInfoForm = this._initForm();
  }


  private _initForm = () => {
    console.log("STO INIT : " + this.user);
    return this._fb.group({
      name: [this.user.name],
      surname: [this.user.surname],
      email: [this.user.email],
      address: [this.user.address],
      birthdate: [this.user.birthdate],
      phoneNumber: [this.user.phoneNumber],
    });
  }

  onSubmit(form: FormGroup) {
    let userRequest = new UserDto(form);
    this.userService.updateUser(userRequest)
      .then(user => {
        this.user = user;
        this.userService.user.next(user);
        localStorage.setItem('currentUser', JSON.stringify(user));
      });
    this._modal.hide();
    this._modal = null;
  }

}
