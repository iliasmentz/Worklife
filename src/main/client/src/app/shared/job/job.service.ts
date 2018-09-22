import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";
import {map} from "rxjs/operators";
import {Subject} from "rxjs";
import {Job, Jobs} from "./job.model";
import {JobDto} from "./job-dto.model";

@Injectable()
export class JobService {
  job = new Subject();

  constructor(private repoService: RepoService) {
  }

  getJobs(): Promise<Jobs> {
    return this.repoService.get('jobs/')
      .pipe(map((jobs: any[]) => {
        return jobs.map(job => this.deserializeJob(job))
      }))
      .toPromise() as Promise<Jobs>;
  }

  updateJob(jobId: number, jobRequest: JobDto) {
    return this.repoService.put("jobs/" + jobId, jobRequest)
      .pipe(map(job => this.deserializeJob(job)))
      .toPromise() as Promise<Job>;
  }

  addJob(jobRequest: JobDto) {
    return this.repoService.post("jobs/", jobRequest)
      .pipe(map(job => this.deserializeJob(job)))
      .toPromise() as Promise<Job>;
  }

  deleteJob(jobId: number) {
    this.repoService.delete("jobs/" + jobId)
      .subscribe(() => {
      }, error => console.log(error));
  }

  private deserializeJob(resp): Job {
    return new Job(resp);
  }
}
