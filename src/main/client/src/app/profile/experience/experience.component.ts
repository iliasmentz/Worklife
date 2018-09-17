import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {ExperienceService} from "./experience.service";
import {Experience} from "../../shared/experience/experience.model";
import {BsModalService, ModalOptions} from "ngx-bootstrap";
import {ExperienceModalComponent} from "./experience-modal/experience-modal.component";
import {ActivatedRoute, Params} from "@angular/router";

const options: ModalOptions = {
  class: 'modal-m',
  backdrop: 'static',
};


@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css'],
})
export class ExperienceComponent implements OnInit, OnDestroy {
  @Input() userId: number;
  @Input() experiences: Experience[];

  constructor(private route: ActivatedRoute,
              private _modal: BsModalService,
              private experienceService: ExperienceService) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      if (this.myProfileUsername(params['username'])) {
        this.experienceService.experience.subscribe((newExperience: Experience) => {
          let updateItem = this.experiences.find(x => x.experienceId === newExperience.experienceId);
          if (updateItem != null) {
            let index = this.experiences.indexOf(updateItem);
            this.experiences[index] = newExperience;
          } else {
            this.experiences.push(newExperience);
          }
        })
      }
    });
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
      experience: null,
      mode: 'Add'
    };

    this._modal.show(ExperienceModalComponent, {...options, initialState});
  }


  openEditModal(experienceId: number) {
    const initialState = {
      experience: this.experiences.find(x => x.experienceId === experienceId),
      mode: 'Update'
    };

    this._modal.show(ExperienceModalComponent, {...options, initialState});
  }


  deleteExperience(experienceId: number) {
    this.experienceService.deleteExperience(experienceId);
    this.experiences = this.experiences.filter(function (obj) {
      return obj.experienceId !== experienceId;
    });
  }

  ngOnDestroy(): void {
    if (this.myProfile()) {
      // this.experienceService.experience.unsubscribe();
    }
  }
}
