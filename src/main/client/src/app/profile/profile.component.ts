import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../shared/user/user.service";
import {User} from "../shared/user/user.model";
import {Skills} from "../shared/skills/skill.model";
import {Educations} from "../shared/education/education.model";
import {Experiences} from "../shared/experience/experience.model";
import {Posts} from "../shared/posts/post.model";
import {BsModalService, ModalOptions} from 'ngx-bootstrap';
import {FileUploadModalComponent} from '../file-upload-modal/file-upload-modal.component';
import {FileInfo} from '../shared/file-upload/file-info.model';
import {FileUploadService} from '../shared/fiile-upload/file-upload.service';

const options: ModalOptions = {
  class: 'modal-sm',
  backdrop: 'static',
};

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./css/bootstrap.css', './css/font-awesome.css', './css/theme.css'],
  encapsulation: ViewEncapsulation.None
})
export class ProfileComponent implements OnInit, OnDestroy {
  user: User;
  username: string;
  skills: Skills;
  educations: Educations;
  experiences: Experiences;
  posts: Posts;

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private _uploadService: FileUploadService,
              private _modal: BsModalService) {
  }

  ngOnInit() {
    this.route.data.subscribe(resolvedData => {
      this.user = resolvedData['user'];
      this.skills = resolvedData['skills'];
      this.educations = resolvedData['educations'];
      this.experiences = resolvedData['experiences'];
      this.posts = resolvedData['posts'];
      if (this.myProfile()) {
        this.userService.user.subscribe((updatedUser: User) => {
          this.user = updatedUser;
        })
      }
    })
  }

  myProfile(): boolean {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.userId === this.user.userId;
  }

  ngOnDestroy(): void {
    if (this.username == null) {
      // this.userService.user.unsubscribe();
    }
  }

  openFileUploadModal() {
    const submit = (fileInfo: FileInfo) => {
      let formData = new FormData();

      formData.append('file', fileInfo.file, fileInfo.name);

      return this._uploadService.upload(formData)
        .then((response) => {
          this.user.imgPath = response.fileDownloadUri;
        })
        .catch((err) => console.log(err));
    };

    const initialState = {
      submitButton: 'Upload',
      title: 'Upload file',
      submit: submit
    };

    this._modal.show(FileUploadModalComponent, {...options, initialState});
  }

}
