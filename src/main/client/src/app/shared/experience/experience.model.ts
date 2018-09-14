export class Experience {
  experienceId: number;
  title: string;
  company: string;
  startingDate: Date;
  endingDate: Date;

  constructor(obj: Experience) {
    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
    console.log(obj);
  }
}

export declare type Experiences = Experience[];

