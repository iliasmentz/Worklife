import {Component, OnInit} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap';
import {FormBuilder, FormGroup} from '@angular/forms';
import {FileInfo} from '../shared/file-upload/file-info.model';

@Component({
  selector: 'app-file-upload-modal',
  templateUrl: './file-upload-modal.component.html'
})
export class FileUploadModalComponent implements OnInit {

  fileForm: FormGroup;

  submitButton: string = 'Add';

  title: string = 'Upload file';

  constructor(private _modal: BsModalRef,
              private _fb: FormBuilder) {
  }

  ngOnInit() {
    this.fileForm = this._initForm();
  }

  private _initForm = (): FormGroup => {
    return this._fb.group({
      file: null
    });
  };

  close() {
    if (this._modal) {
      this._modal.hide();
      this._modal = null;
    }
  }

  onSubmit(form: FormGroup) {
    const fileInfo: FileInfo = form.get('file').value;

    this.submit(fileInfo)
      .then(() => this.close())
      .catch(() => {
      });
  }

  submit(_: any) {
    return null;
  }
}
