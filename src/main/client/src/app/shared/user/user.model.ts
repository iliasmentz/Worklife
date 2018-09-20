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

  constructor(obj: User) {

    Object.keys(obj).forEach(key => {
      this[key] = obj[key];
    });

    this.username = this.username || '';
    this.imagePath = this.imagePath || 'assets/img/user.svg';
  }
}
