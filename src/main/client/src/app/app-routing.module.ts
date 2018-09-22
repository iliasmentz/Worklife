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
import {JobsComponent} from "./jobs/jobs.component";
import {ChatComponent} from "./chat/chat.component";
import {ConversationComponent} from "./chat/conversation/conversation.component";
import {ConversationResolver} from "./chat/conversation/conversation.resolver";

const appRoutes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard], children: [
      PROFILE_ROUTE,
      {path: '', component: NewsfeedComponent, pathMatch: 'full'},
      {path: 'connections/:id', component: ConnectionsComponent, pathMatch: 'full'},
      {path: 'jobs', component: JobsComponent, pathMatch: 'full'},
      {
        path: 'messages', component: ChatComponent, children: [
          {path: ':userId', component: ConversationComponent, resolve: {conversation: ConversationResolver}}
        ]
      }
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
