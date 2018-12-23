import {Component, Input, OnInit} from '@angular/core';
import {Education} from "../../shared/education/education.model";
import {EducationService} from "./education.service";
import {EducationModalComponent} from "./education-modal/education-modal.component";
import {BsModalService, ModalOptions} from "ngx-bootstrap";
import {ActivatedRoute, Params} from "@angular/router";

const options: ModalOptions = {
  class: 'modal-m',
  backdrop: 'static',
};

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css'],
})
export class EducationComponent implements OnInit {
  @Input() userId: number;
  @Input() educations: Education[];


  constructor(private route: ActivatedRoute,
              private _modal: BsModalService,
              private educationService: EducationService) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      if (this.myProfileUsername(params['username'])) {
        this.educationService.education.subscribe((newEducation: Education) => {
          let updateItem = this.educations.find(x => x.educationId === newEducation.educationId);
          if (updateItem != null) {
            let index = this.educations.indexOf(updateItem);
            this.educations[index] = newEducation;
          } else {
            this.educations.push(newEducation);
          }
        })
      }
    })

  }

  myProfileUsername(username: string): boolean {
    let user = JSON.parse(localStorage.getItem('currentUser'));
    return user.username === username;
  }

  myProfile(): boolean {
    let user = JSON.parse(localStorage.getItem('currentUser'));
    return user.userId === this.userId;
  }

  openAddModal() {
    const initialState = {
      education: null,
      mode: 'Add'
    };

    this._modal.show(EducationModalComponent, {...options, initialState});
  }


  openEditModal(educationId: number) {
    const initialState = {
      education: this.educations.find(x => x.educationId === educationId),
      mode: 'Update'
    };

    this._modal.show(EducationModalComponent, {...options, initialState});
  }


  deleteEducation(educationId: number) {
    this.educationService.deleteEducation(educationId);
    this.educations = this.educations.filter(function (obj) {
      return obj.educationId !== educationId;
    });
  }

  ngOnDestroy(): void {
    if (this.myProfile()) {
      // this.educationService.education.unsubscribe();
    }
  }
}
