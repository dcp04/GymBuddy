import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Services/AuthService/auth.service';
import { TokenService } from 'src/app/Services/TokenService/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  FormUser!: FormGroup;
  loginUser = {
    email: '',
    password: ''
  }
  email: string='';
  password: string='';
  error = ''
  constructor( private service: AuthService,private tokenservice: TokenService, private fb: FormBuilder, private router: Router) {

  }

 //Creation of the form group
 ngOnInit() {
  this.FormUser = this.fb.group({
    email: ['', Validators.required],
    password: ['', Validators.required],
  },{updateOn:'submit'});
}

  login() {
    //Method to login a user
    if (this.FormUser.valid) {
       this.loginUser = {
        email: this.FormUser.get('email')?.value,
        password: this.FormUser.get('password')?.value,
      };
      //Usage of our service to post the user 
      this.service.login(this.loginUser)
        .subscribe({
          next: (res) => {
            console.log(res)
            this.tokenservice.setUserDetails(res.token, res.rol)
            this.error = ''
            this.router.navigate(['/'])
          },
          error: (err) => {
            console.log(err)
            this.error = 'Invalid credentials, please try again'
          }
        })
    } 
    //If form was not valid, this text gets visible in the html
    else {
      console.log('Invalid form. Check fields and try again.')
    }
  }

  
}
