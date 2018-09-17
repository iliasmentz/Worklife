import {Route} from "@angular/router";
import {ProfileComponent} from "./profile.component";
import {SkillsResolver} from "./resolvers/skills.resolver";
import {ProfileResolver} from "./profile.resolver";
import {ExperienceResolver} from "./resolvers/experience.resolver";
import {EducationResolver} from "./resolvers/education.resolver";


const resolveLatestProf = {
  // basicInfo: BasicInfoResolver,
  educations: EducationResolver,
  experiences: ExperienceResolver,
  skills: SkillsResolver,

};

export const PROFILE_ROUTE: Route = {
  path: 'profile/:username',
  resolve: {
    user: ProfileResolver
  },
  data: {
    pageHeader: 'Profile',
    optionalDescription: 'User\'s profile.',
  },
  children: [{
    path: '',
    component: ProfileComponent,
    resolve: resolveLatestProf
  }]
};
