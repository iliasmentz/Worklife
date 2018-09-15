import {Component, Input, OnInit} from '@angular/core';
import {Skill} from "../../shared/skills/skill.model";
import {SkillService} from "./skill.service";

@Component({
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css'],
})
export class SkillsComponent implements OnInit {
  @Input() userId: number;
  skills: Skill[];


  constructor(private skillService: SkillService) { }

  ngOnInit() {
    this.skillService.getSkill(this.userId)
      .then(skills => {
        this.skills = skills;
      });
  }

  myProfile(): boolean {
    let user = JSON.parse(localStorage.getItem('currentUser'));
    return user.userId === this.userId;
  }

  getType(value): string {
    let type: string;
    console.log(value);
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

}
