export class User {
  address: string;
  birthdate: Date;
  email: string;
  imagePath: string;
  name: string;
  surname: string;
  displayName: string;
  phoneNumber: string;
  userId: number;
  username: string;
  dateCreated: Date;
  role: number;

  constructor(obj: User) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });

    this.username = this.username || '';
    this.imagePath = this.imagePath || 'assets/img/user.svg';
  }
}

export declare type Users = User[];
