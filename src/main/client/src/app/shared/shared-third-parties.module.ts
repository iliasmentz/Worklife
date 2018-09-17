import {NgModule} from "@angular/core";
import {BsDatepickerModule, BsDropdownModule, ModalModule, ProgressbarModule} from "ngx-bootstrap";

@NgModule({
  imports: [
    BsDatepickerModule.forRoot(),
    ProgressbarModule.forRoot(),
    ModalModule.forRoot(),
    BsDropdownModule.forRoot()
  ],
  exports: [
    BsDatepickerModule,
    ProgressbarModule,
    ModalModule,
    BsDropdownModule
  ]
})
export class SharedThirdPartiesModule {

}
