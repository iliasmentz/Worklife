import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {LoginComponent} from "./welcome/login/login.component";
import {HomeComponent} from "./home/home.component";
import {AuthGuard} from "./shared/auth/auth.guard";
import {WelcomeComponent} from "./welcome/welcome.component";
import {RegisterComponent} from "./welcome/register/register.component";
import {NewsfeedComponent} from "./newsfeed/newsfeed.component";
import {PROFILE_ROUTE} from "./profile/profile.route";
import {ConnectionsComponent} from "./connections/connections.component";

const appRoutes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard], children: [
      PROFILE_ROUTE,
      {path: '', component: NewsfeedComponent, pathMatch: 'full'},
      {path: 'connections/:id', component: ConnectionsComponent, pathMatch: 'full'},
    ]},
  {path: 'welcome', component: WelcomeComponent, children: [
      {path: 'register', component: RegisterComponent},
      {path: '', component: LoginComponent, pathMatch: 'full'}
    ]},
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, {useHash: true, enableTracing: false}),
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
