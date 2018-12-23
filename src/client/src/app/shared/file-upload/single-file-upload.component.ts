import {Component, forwardRef, Input, OnDestroy, OnInit} from '@angular/core';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';
import {ControlValueAccessor, NG_VALUE_ACCESSOR} from '@angular/forms';

/**
 * Register the component by pushing it to the global NG_VALUE_ACCESSOR provider
 */
const FILE_UPLOAD_VALUE_ACCESSOR: any = {
  provide: NG_VALUE_ACCESSOR,
  useExisting: forwardRef(() => SingleFileUploadComponent),
  multi: true
};

@Component({
  selector: 'app-single-file-upload',
  templateUrl: './single-file-upload.html',
  styleUrls: ['./single-file-upload.component.scss'],
  providers: [FILE_UPLOAD_VALUE_ACCESSOR]
})
export class SingleFileUploadComponent implements OnInit, OnDestroy, ControlValueAccessor {
  @Input()
  maximumFileSize: number;

  fileName: string;

  fileBlobUrl: SafeResourceUrl;

  isOverSized: boolean;

  private _fileDataUrl: string;

  private _tmpFileBlobUrl: string;

  get maxSizeInMB() {
    return (this.maximumFileSize / 1048576).toFixed(2);
  }

  constructor(private _sanitizer: DomSanitizer) {
  }

  ngOnInit() {
    this.fileName = '';
    this._tmpFileBlobUrl = '';
  }

  ngOnDestroy() {
    this.clearSelection();
  }

  get hasSelection(): boolean {
    return !!this.fileName;
  }

  handleFileInput(element) {
    this.clearSelection();

    const file: File = element.files[0];
    this.fileName = file.name;
    this._tmpFileBlobUrl = URL.createObjectURL(file);
    this.fileBlobUrl = this._sanitizer.bypassSecurityTrustResourceUrl(this._tmpFileBlobUrl);

    const reader = new FileReader();
    reader.onload = e => {
      const fileSize = (e as ProgressEvent).total;

      this.isOverSized = this.maximumFileSize && fileSize > this.maximumFileSize;

      this._fileDataUrl = (e.target as FileReader).result;

      this.propagateChange({name: this.fileName, size: fileSize, file: file, type: file.type});
    };
    reader.readAsDataURL(file);

    element.value = '';
  }

  clearSelection() {
    this.fileName = '';
    this._fileDataUrl = '';
    this.fileBlobUrl = undefined;
    URL.revokeObjectURL(this._tmpFileBlobUrl);
    this._tmpFileBlobUrl = '';
  }

  propagateChange(_: any) {
  }

  registerOnChange(fn: any): void {
    this.propagateChange = fn;
  }

  registerOnTouched(fn: any): void {
  }

  setDisabledState(isDisabled: boolean): void {
  }

  writeValue(obj: any): void {
  }

}
