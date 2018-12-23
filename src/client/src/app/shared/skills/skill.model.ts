export class Skill {
  skillId: number;
  name: string;
  level: number;
  visible: string;

  constructor(obj: Skill) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });
  }
}

export declare type Skills = Skill[];
