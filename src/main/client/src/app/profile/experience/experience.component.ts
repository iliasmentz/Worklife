import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {ExperienceService} from "./experience.service";
import {Experience} from "../../shared/experience/experience.model";

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css'],
  encapsulation: ViewEncapsulation.None
})
export class ExperienceComponent implements OnInit {
  @Input() userId: number;
  experiences: Experience[];

  constructor(private experienceService: ExperienceService) { }

  ngOnInit() {
    this.experienceService.getExperience(this.userId)
      .then(experiences => {
        this.experiences = experiences;
      });
  }

}
