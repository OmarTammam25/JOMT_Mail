import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserDTO } from '../DTO/UserDTO';
import { UserService } from '../Service/User/user.service';


@Component({
  selector: 'app-login',
  host: {
    '[style.width]' : "'100%'", 
    '[style.display]' : "'flex'", 
  },
  styles : [`:host {
      background-image: url(../../assets/sunrise.jpg);
      background-attachment: fixed;
      background-size: cover;
      background-blend-mode: darken;
      position: relative;
    }`],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {

  constructor(public userService: UserService) { }

  hidePassword = true
  important = "Must Enter a Value!"
  mailValid = "Must Enter a Valid Email!"
  limitLength = "15 Characters Only!"
  isValidLogin = false;

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

    this.userService.login(user).subscribe(data => {
      this.checkUserCredentials(data);
      this.userService.userId = data.id;
      this.userService.userEmail = data.email;
      this.userService.folders = data.folderNames;
      console.log(this.userService.folders);
      console.log("user log in: ", this.userService.userId)
      // this.userService.inboxId = data.inboxId;
    })

  }

  checkUserCredentials(userDto: UserDTO){
    if(userDto.requestState === 'false')
      this.isValidLogin = false;
    else
      this.isValidLogin = true;
  }

  isValid(){
    console.log(this.isValid);
    return this.isValid;
  }
  
  validation(){
    return this.isValidLogin ? "/Mails" : "/login";
  }
}
