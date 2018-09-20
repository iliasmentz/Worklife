export class Experience {
  experienceId: number;
  title: string;
  company: string;
  startDate: Date;
  endDate: Date;
  visible: number;

  constructor(obj: Experience) {
    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type Experiences = Experience[];

