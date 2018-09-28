import {Injectable} from "@angular/core";
import {RepoService} from "../repo/repo.service";
import {map} from "rxjs/operators";
import {Subject} from "rxjs";
import {Job, Jobs} from "./job.model";
import {JobDto} from "./job-dto.model";
import {JobApplication, JobApplications} from "./job-application.model";

@Injectable()
export class JobService {
  job = new Subject();

  constructor(private _repoService: RepoService) {
  }

  getJobs(): Promise<Jobs> {
    return this._repoService.get('jobs/')
      .pipe(map((jobs: any[]) => {
        return jobs.map(job => this.deserializeJob(job))
      }))
      .toPromise() as Promise<Jobs>;
  }

  updateJob(jobId: number, jobRequest: JobDto) {
    return this._repoService.put("jobs/" + jobId, jobRequest)
      .pipe(map(job => this.deserializeJob(job)))
      .toPromise() as Promise<Job>;
  }

  addJob(jobRequest: JobDto) {
    return this._repoService.post("jobs/", jobRequest)
      .pipe(map(job => this.deserializeJob(job)))
      .toPromise() as Promise<Job>;
  }

  applyJob(jobId: number) {
    return this._repoService.post("jobs/apply/" + jobId)
      .pipe(map(job => this.deserializeJobApplication(job)))
      .toPromise() as Promise<JobApplication>;
  }

  getJobApplications(): Promise<JobApplications> {
    return this._repoService.get("jobs/apply/myapplications")
      .pipe(map((jobApplications: any[]) => {
        return jobApplications.map(jobApplication => this.deserializeJobApplication(jobApplication))
      }))
      .toPromise() as Promise<JobApplications>;
  }

  deleteJob(jobId: number) {
    this._repoService.delete("jobs/" + jobId)
      .subscribe(() => {
      }, error => console.log(error));
  }

  private deserializeJobApplication(resp): JobApplication {
    return new JobApplication(resp);
  }

  private deserializeJob(resp): Job {
    return new Job(resp);
  }
}
