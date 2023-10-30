import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserDTO } from '../DTO/UserDTO';
import { UserService } from '../Service/User/user.service';

@Component({
  selector: 'app-register',
  host: {
    '[style.width]' : "'100%'", 
    '[style.display]' : "'flex'", 
  },
  styles : [`:host {
      background-image: url(../../assets/login-background.jpg);
      background-attachment: fixed;
      background-size: cover;
    }`],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export default class RegisterComponent {

  constructor(public userService: UserService) { }

  hidePassword = true
  important = "Must Enter a Value!"
  mailValid = "Must Enter a Valid Email!"
  limitLength = "15 Characters Only!"

  userGroup = new FormGroup({
    userName: new FormControl('', [Validators.required, Validators.maxLength(15)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });
  userError(field: string){ return this.userGroup.get(field)?.invalid }
  userErrorMessage(field: string){ 
    return this.userGroup.get(field)?.hasError('required') ? this.important : 
    this.userGroup.get(field)?.hasError('email') ? this.mailValid : 
    this.userGroup.get(field)?.hasError('maxlength') ? this.limitLength : '';
  }

  submitUser(){
    let user = <UserDTO>{
      userName: this.userGroup.controls.userName.value,
      email: this.userGroup.controls.email.value,
      password: this.userGroup.controls.password.value
    }
    this.userService.create(user).subscribe((data) => {
     
    })
  }

  ff = false
  validation(){
    return this.ff ? "/Mails" : "/login";
  }
}
