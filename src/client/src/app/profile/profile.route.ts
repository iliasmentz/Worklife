import {Route} from "@angular/router";
import {ProfileComponent} from "./profile.component";
import {SkillsResolver} from "./resolvers/skills.resolver";
import {ProfileResolver} from "./profile.resolver";
import {ExperienceResolver} from "./resolvers/experience.resolver";
import {EducationResolver} from "./resolvers/education.resolver";
import {PostsResolver} from "./resolvers/posts.resolver";
import {MyFriendsResolver} from "./resolvers/my-friends.resolver";
import {UserFriendsResolver} from "./resolvers/user-friends.resolver";


const resolveLatestProf = {
  // basicInfo: BasicInfoResolver,
  educations: EducationResolver,
  experiences: ExperienceResolver,
  skills: SkillsResolver,
  posts: PostsResolver,
  myFriends: MyFriendsResolver,
  userFriends: UserFriendsResolver

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
