import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../shared/user/user.service";
import {User} from "../shared/user/user.model";
import {Skills} from "../shared/skills/skill.model";
import {Educations} from "../shared/education/education.model";
import {Experiences} from "../shared/experience/experience.model";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./css/bootstrap.css', './css/font-awesome.css', './css/theme.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProfileComponent implements OnInit, OnDestroy {
  user: User;
  username: string;
  skills: Skills;
  educations: Educations;
  experiences: Experiences;

  constructor(private route: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit() {
    this.route.data.subscribe(resolvedData => {
      this.user = resolvedData['user'];
      this.skills = resolvedData['skills'];
      this.educations = resolvedData['educations'];
      this.experiences = resolvedData['experiences'];
      }
    )
  }

  ngOnDestroy(): void {
    if (this.username == null) {
      // this.userService.user.unsubscribe();
    }
  }


}
