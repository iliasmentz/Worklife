import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {LoginComponent} from "./welcome/login/login.component";
import {HomeComponent} from "./home/home.component";
import {AuthGuard} from "./shared/auth/auth.guard";
import {WelcomeComponent} from "./welcome/welcome.component";
import {RegisterComponent} from "./welcome/register/register.component";
import {ProfileComponent} from "./profile/profile.component";
import {NewsfeedComponent} from "./newsfeed/newsfeed.component";


const appRoutes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard], children: [
      {path: 'profile/:username', component: ProfileComponent},
      {path: 'profile', component: ProfileComponent},
      {path: '', component: NewsfeedComponent, pathMatch: 'full'},
    ]},
  {path: 'welcome', component: WelcomeComponent, children: [
      {path: 'register', component: RegisterComponent},
      {path: '', component: LoginComponent, pathMatch: 'full'}
    ]},
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, {useHash: true, enableTracing: false})
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
