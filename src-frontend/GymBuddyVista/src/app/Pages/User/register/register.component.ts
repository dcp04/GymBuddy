import { Component } from '@angular/core';
import { EmailValidator, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Role, User } from 'src/app/Models/user';
import { AuthService } from 'src/app/Services/AuthService/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  FormUser!: FormGroup;
  text = '';

  constructor(
    private fb: FormBuilder, 
    private authService: AuthService,
    private route: Router
  ) {}

  ngOnInit() {
    this.validators();
  }

  validators() {
    this.FormUser = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      nombre: ['', [Validators.required, Validators.pattern(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s-]+$/), Validators.maxLength(25)]],
      apellidos: ['', [Validators.required, Validators.pattern(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s-]+$/), Validators.maxLength(25)]],  
      password: ['', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^\da-zA-Z]).{8,}$/)]],
      secondPassword: ['', Validators.required],
      estatura: ['', [Validators.required, Validators.min(1)]],
      peso: ['', [Validators.required]],
    }, { 
      validator: this.passwordMatchValidator 
    });
  }

  passwordMatchValidator(group: FormGroup): ValidationErrors | null {
    const password = group.get('password')?.value;
    const secondPassword = group.get('secondPassword')?.value;
    return password === secondPassword ? null : { passwordMismatch: true };
  }

  saveUser() {
    this.FormUser.markAllAsTouched();

    if (this.FormUser.valid) {
      const userData: User = {
        id: 0,
        email: this.FormUser.get('email')?.value,
        nombre: this.FormUser.get('nombre')?.value,
        apellidos: this.FormUser.get('apellidos')?.value,
        password: this.FormUser.get('password')?.value,
        estatura: this.FormUser.get('estatura')?.value,
        peso: this.FormUser.get('peso')?.value,
        roles: this.FormUser.get('roles')?.value === 'Admin' ? Role.ADMIN : Role.USER,
      };
      
      this.authService.signup(userData)
        .subscribe({
          next: (response) => {
            this.text = 'User successfully registered';
            this.FormUser.reset();
            this.route.navigate(['']);
          },
          error: (err) => {
            console.error(err);
            this.text = 'An error occurred during registration. Please try again.';
          }
        });
    } else {
      this.text = 'Invalid form. Check fields and try again.';
    }
  }
}