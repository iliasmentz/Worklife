export class JobApplication {
  userId: number;
  jobId: number;

  constructor(obj: JobApplication) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type JobApplications = JobApplication[];
