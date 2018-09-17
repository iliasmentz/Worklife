import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavbarComponent} from './layout/navbar/navbar.component';
import {HomeComponent} from './home/home.component';
import {Globals} from "./globals";
import {WelcomeComponent} from './welcome/welcome.component';
import {LoginComponent} from './welcome/login/login.component';
import {AppRoutingModule} from "./app-routing.module";
import {AuthGuard} from "./shared/auth/auth.guard";
import {AuthService} from "./shared/auth/auth.service";
import {RegisterComponent} from './welcome/register/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RepoService} from "./shared/repo/repo.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptor} from "./shared/auth/auth.interceptor";
import {UserService} from "./shared/user/user.service";
import {ProfileComponent} from './profile/profile.component';
import {NewsfeedComponent} from './newsfeed/newsfeed.component';
import {BasicInfoComponent} from './profile/basic-info/basic-info.component';
import {ExperienceComponent} from './profile/experience/experience.component';
import {EducationComponent} from './profile/education/education.component';
import {BsDatepickerModule, BsDropdownModule, BsModalRef, ModalModule, ProgressbarModule} from "ngx-bootstrap";
import {EducationService} from "./profile/education/education.service";
import {MomentModule} from "angular2-moment";
import {SkillsComponent} from "./profile/skills/skills.component";
import {PostsComponent} from "./profile/posts/posts.component";
import {FriendsComponent} from "./profile/friends/friends.component";
import {ExperienceService} from "./profile/experience/experience.service";
import {SkillService} from "./profile/skills/skill.service";
import {BasicInfoModalComponent} from './profile/basic-info/basic-info-modal/basic-info-modal.component';
import {SkillsModalComponent} from "./profile/skills/skills-modal/skills-modal.component";
import {EducationResolver} from "./profile/resolvers/education.resolver";
import {ProfileResolver} from "./profile/profile.resolver";
import {SkillsResolver} from "./profile/resolvers/skills.resolver";
import {ExperienceResolver} from "./profile/resolvers/experience.resolver";
import {ExperienceModalComponent} from "./profile/experience/experience-modal/experience-modal.component";
import {EducationModalComponent} from "./profile/education/education-modal/education-modal.component";
import {PostsResolver} from "./profile/resolvers/posts.resolver";
import {PostService} from "./shared/posts/post.service";
import {ScrollbarModule} from "ngx-scrollbar";


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    WelcomeComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    NewsfeedComponent,
    BasicInfoComponent,
    ExperienceComponent,
    EducationComponent,
    SkillsComponent,
    PostsComponent,
    FriendsComponent,
    BasicInfoModalComponent,
    SkillsModalComponent,
    ExperienceModalComponent,
    EducationModalComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MomentModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    ScrollbarModule,
    BsDatepickerModule.forRoot(),
    ProgressbarModule.forRoot(),
    ModalModule.forRoot(),
    BsDropdownModule.forRoot(),
  ],
  entryComponents: [
    BasicInfoModalComponent,
    SkillsModalComponent,
    ExperienceModalComponent,
    EducationModalComponent
  ],
  providers: [
    Globals, AuthGuard, AuthService, RepoService, UserService,
    EducationService, ExperienceService, SkillService, PostService, BsModalRef,
    EducationResolver, ProfileResolver, SkillsResolver, ExperienceResolver, PostsResolver,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },],
  bootstrap: [AppComponent]
})
export class AppModule {
}

