import {Injectable} from "@angular/core";
import {RepoService} from "../shared/repo/repo.service";
import {HttpClient} from "@angular/common/http";
// @ts-ignore
import saveAs from 'file-saver';

@Injectable()
export class AdminService {
  constructor(private _http: HttpClient,
              private _repoService: RepoService) {
  }

  public downloadXMLFile() {
    this._http.get(this._repoService.API_ENDPOINT + this._repoService.API_PREFIX +
      'admin/get-xml/', {responseType: 'text'}).subscribe((r) => {
      let blob = new Blob([r], {type: 'application/xml'});
      saveAs(blob, "Worklife-Export.xml");
    });
  }
}
