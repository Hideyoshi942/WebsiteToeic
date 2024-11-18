export default class User {
  constructor(username, email, password, address, confirmpassword, phonenumber) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.address = address;
    this.confirmpassword = confirmpassword;
    this.phonenumber = phonenumber;
  }
}