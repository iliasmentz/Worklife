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
import { ProfileComponent } from './profile/profile.component';
import { NewsfeedComponent } from './newsfeed/newsfeed.component';
import { BasicInfoComponent } from './profile/basic-info/basic-info.component';
import { ExperienceComponent } from './profile/experience/experience.component';
import { EducationComponent } from './profile/education/education.component';
import { SkillsComponent } from './profile/skills/skills.component';
import { PostsComponent } from './profile/posts/posts.component';
import { FriendsComponent } from './profile/friends/friends.component';

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
    FriendsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [
    Globals, AuthGuard, AuthService, RepoService, UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },],
  bootstrap: [AppComponent]
})
export class AppModule {
}
