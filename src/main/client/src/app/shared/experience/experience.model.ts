export class Experience {
  experienceId: number;
  title: string;
  company: string;
  startDate: Date;
  endDate: Date;

  constructor(obj: Experience) {
    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
    console.log(obj);
  }
}

export declare type Experiences = Experience[];

