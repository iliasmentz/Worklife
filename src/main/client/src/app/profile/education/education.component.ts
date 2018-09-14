import {Component, Input, OnInit} from '@angular/core';
import {Education} from "../../shared/education/education.model";
import {EducationService} from "./education.service";

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['../css/bootstrap.css', '../css/font-awesome.css', '../css/theme.css'],
})
export class EducationComponent implements OnInit {
  @Input() userId: number;
  educations: Education[];


  constructor(private educationService: EducationService) { }

  ngOnInit() {
    this.educationService.getEducation(this.userId)
      .then(educations => {
        this.educations = educations;
      });
  }

}
