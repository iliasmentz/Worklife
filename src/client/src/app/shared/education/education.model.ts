export class Education {
  educationId: number;
  universityDegree: string;
  universityName: string;
  startDate: Date;
  endDate: Date;
  visible: number;

  constructor(obj: Education) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type Educations = Education[];
