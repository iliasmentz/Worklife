import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Skill} from "../../shared/skills/skill.model";
import {SkillService} from "./skill.service";
import {SkillsModalComponent} from "./skills-modal/skills-modal.component";
import {BsModalService, ModalOptions} from "ngx-bootstrap";
import {ActivatedRoute, Params} from "@angular/router";


const options: ModalOptions = {
  class: 'modal-m',
  backdrop: 'static',
};

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css'],
})
export class SkillsComponent implements OnInit, OnDestroy {
  @Input() userId: number;
  @Input() skills: Skill[];


  constructor(private route: ActivatedRoute,
              private _modal: BsModalService,
              private skillService: SkillService) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      if (this.myProfileUsername(params['username'])) {
        this.skillService.skill.subscribe((newSkill: Skill) => {
          let updateItem = this.skills.find(x => x.skillId === newSkill.skillId);
          if (updateItem != null) {
            let index = this.skills.indexOf(updateItem);
            this.skills[index] = newSkill;
          } else {
            this.skills.push(newSkill);
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
      skill: null,
      mode: 'Add'
    };

    this._modal.show(SkillsModalComponent, {...options, initialState});
  }

  openEditModal(skillId: number) {
    const initialState = {
      skill: this.skills.find(x => x.skillId === skillId),
      mode: 'Update'
    };

    this._modal.show(SkillsModalComponent, {...options, initialState});
  }


  deleteSkill(skillId: number) {
    this.skillService.deleteSkill(skillId);
    this.skills = this.skills.filter(function (obj) {
      return obj.skillId !== skillId;
    });
  }

  ngOnDestroy(): void {
    if (this.myProfile()) {
      // this.skillService.skill.unsubscribe();
    }
    this.skills.length = 0;
  }

  getType(value): string {
    let type: string;
    if (value === 1) {
      type = 'danger';
    } else if (value === 2) {
      type = 'warning';
    } else if (value === 3) {
      type = 'info';
    } else if (value === 4) {
      type = 'info';
    } else {
      type = 'success';
    }
    return type;
  }

  getText(value): string {
    let text: string;
    if (value === 1) {
      text = 'Fundamental';
    } else if (value === 2) {
      text = 'Basic';
    } else if (value === 3) {
      text = 'Intermediate';
    } else if (value === 4) {
      text = 'Advanced';
    } else {
      text = 'Proficient';
    }
    return text;
  }

}
