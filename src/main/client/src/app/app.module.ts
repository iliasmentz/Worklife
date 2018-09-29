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
import {
  AccordionModule,
  BsDatepickerModule,
  BsDropdownModule,
  BsModalRef,
  ModalModule,
  ProgressbarModule,
  TabsModule
} from "ngx-bootstrap";
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
import {PostModalComponent} from "./profile/posts/post-modal/post-modal.component";
import {PostFormComponent} from './newsfeed/post-form/post-form.component';
import {SingleFileUploadComponent} from './shared/file-upload/single-file-upload.component';
import {FileUploadModalComponent} from './file-upload-modal/file-upload-modal.component';
import {FileUploadService} from './shared/fiile-upload/file-upload.service';
import {PostListComponent} from './newsfeed/post-list/post-list.component';
import {CommentService} from "./shared/comments/comment.service";
import {CommentComponent} from './newsfeed/post-list/comment/comment.component';
import {LikeService} from "./shared/likes/like.service";
import {LikeComponent} from './newsfeed/post-list/like/like.component';
import {ConnectionsComponent} from './connections/connections.component';
import {ConnectionService} from "./shared/connections/connection.service";
import {JobsComponent} from './jobs/jobs.component';
import {MatCheckboxModule} from "@angular/material";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {JobService} from './shared/job/job.service';
import {JobModalComponent} from './jobs/job-modal/job-modal.component';
import {NotificationService} from "./shared/notifications/notification.service";
import {MyFriendsResolver} from "./profile/resolvers/my-friends.resolver";
import {UserFriendsResolver} from "./profile/resolvers/user-friends.resolver";
import {ChatComponent} from './chat/chat.component';
import {MessageService} from "./shared/messages/message.service";
import {ConversationComponent} from './chat/conversation/conversation.component';
import {ConversationResolver} from "./chat/conversation/conversation.resolver";
import {VgCoreModule} from "videogular2/core";
import {VgControlsModule} from "videogular2/controls";
import {VgOverlayPlayModule} from "videogular2/overlay-play";
import {VgBufferingModule} from "videogular2/buffering";


@NgModule({
  declarations: [
    AppComponent,
    BasicInfoComponent,
    BasicInfoModalComponent,
    EducationComponent,
    EducationModalComponent,
    ExperienceComponent,
    ExperienceModalComponent,
    FileUploadModalComponent,
    FriendsComponent,
    HomeComponent,
    LoginComponent,
    NavbarComponent,
    NewsfeedComponent,
    PostFormComponent,
    PostModalComponent,
    PostsComponent,
    ProfileComponent,
    RegisterComponent,
    SingleFileUploadComponent,
    SkillsComponent,
    SkillsModalComponent,
    ExperienceModalComponent,
    EducationModalComponent,
    PostModalComponent,
    PostFormComponent,
    PostListComponent,
    CommentComponent,
    LikeComponent,
    WelcomeComponent,
    ConnectionsComponent,
    JobsComponent,
    JobModalComponent,
    ChatComponent,
    ConversationComponent,
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
    AccordionModule.forRoot(),
    TabsModule.forRoot(),
    BrowserAnimationsModule,
    MatCheckboxModule,
    VgCoreModule,
    VgControlsModule,
    VgOverlayPlayModule,
    VgBufferingModule
  ],
  entryComponents: [
    BasicInfoModalComponent,
    FileUploadModalComponent,
    SkillsModalComponent,
    JobModalComponent,
    ExperienceModalComponent,
    EducationModalComponent,
    PostModalComponent,
    CommentComponent,
    LikeComponent
  ],
  providers: [
    Globals, AuthGuard, AuthService, RepoService, UserService, JobService, NotificationService, MessageService,
    EducationService, ExperienceService, SkillService, LikeService, ConnectionService,
    PostService, CommentService, BsModalRef, FileUploadService,
    EducationResolver, ProfileResolver, SkillsResolver, ExperienceResolver, PostsResolver, MyFriendsResolver,
    UserFriendsResolver, ConversationResolver,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },],
  bootstrap: [AppComponent]
})
export class AppModule {
}

