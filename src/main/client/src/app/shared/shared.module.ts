import {NgModule} from "@angular/core";
import {SharedThirdPartiesModule} from "./shared-third-parties.module";
import {Globals} from "../globals";
import {AuthGuard} from "./auth/auth.guard";
import {AuthService} from "./auth/auth.service";
import {RepoService} from "./repo/repo.service";
import {UserService} from "./user/user.service";
import {SharedLibsModule} from "./shared-lib.module";
import {ProfileResolver} from "../profile/profile.resolver";

@NgModule({
  imports: [SharedLibsModule, SharedThirdPartiesModule],
  declarations: [],
  providers: [
    Globals,
    AuthGuard,
    AuthService,
    RepoService,
    UserService,
    ProfileResolver
  ]
})
export class SharedModule {

}
