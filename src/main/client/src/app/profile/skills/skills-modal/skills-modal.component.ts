import {Component, OnInit} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Skill} from "../../../shared/skills/skill.model";
import {SkillService} from "../skill.service";
import {SkillDto} from "../../../shared/skills/skill-dto.model";

@Component({
  selector: 'app-skills-modal',
  templateUrl: './skills-modal.component.html'
})
export class SkillsModalComponent implements OnInit {

  mode: string;
  skill: Skill;
  skillForm: FormGroup;

  constructor(public _modal: BsModalRef, private _fb: FormBuilder,
              private skillService: SkillService) {
  }

  close() {
    if (this._modal) {
      this._modal.hide();
      this._modal = null;
    }
  }

  ngOnInit() {
    if (this.mode === 'Update') {
      this.skillForm = this._editForm();
    } else {
      this.skillForm = this._addForm();
    }
  }


  private _editForm = () => {
    return this._fb.group({
      name: [this.skill.name, Validators.required],
      level: [this.skill.level, Validators.required],
      visible: [this.skill.visible, Validators.required],
    });
  }

  private _addForm = () => {
    return this._fb.group({
      name: [null, Validators.required],
      level: [null, Validators.required],
      visible: [null, Validators.required],
    });
  }

  onSubmit(form: FormGroup) {
    let skillRequest = new SkillDto();
    skillRequest.load(form);

    if (this.mode === 'Update') {
      this.skillService.updateSkill(this.skill.skillId, skillRequest)
        .then(skill => {
          this.skill = skill;
          this.skillService.skill.next(skill);
        });
    } else {
      this.skillService.addSkill(skillRequest)
        .then(skill => {
          this.skill = skill;
          this.skillService.skill.next(skill);
        });
    }
    this._modal.hide();
    this._modal = null;
  }

}
