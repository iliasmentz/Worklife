export class Education {
  id: number;
  universityDegree: string;
  universityName: string;
  startDate: Date;
  endDate: Date;

  constructor(obj: Education) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type Educations = Education[];
