import {Moment} from 'moment';


export class User {
  address: string;
  birthdate: Moment;
  email: string;
  imgPath: string;
  name: string;
  phoneNumber: string;
  userId: number;
  username: string;

  constructor(obj: User) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });

    this.username = this.username || '';
    this.imgPath = this.imgPath || 'assets/img/user.svg';
  }
}
