import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HlmButtonDirective } from '@spartan-ng/ui-button-helm';
import { HlmInputDirective } from '@spartan-ng/ui-input-helm';
import { AuthService } from '../../../services/auth/auth.service';
import { Router, RouterLink } from '@angular/router';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ApiService } from '../../../services/api/api.service';

@Component({
  selector: 'register',
  standalone: true,
  imports: [
    CommonModule,
    HlmButtonDirective,
    HlmInputDirective,
    RouterLink,
    ReactiveFormsModule,
  ],
  templateUrl: './register.component.html',
})
export class RegisterComponent {
  registerForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
    passwordConfirmation: new FormControl('', [Validators.required]),
  });
  error: string = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  signInWithGoogle() {
    this.authService
      .signInWithGoogle()
      .then(() => {
        this.router.navigateByUrl('home');
      })
      .catch((err) => {
        console.log(err);
      });
  }

  signUpWithEmailAndPass() {
    const email = this.registerForm.get('email')?.value;
    const password = this.registerForm.get('password')?.value;
    const passwordConfirmation = this.registerForm.get(
      'passwordConfirmation'
    )?.value;

    if (email === '' || password === '' || passwordConfirmation === '') {
      this.error = 'Please fill out all fields';
      return;
    }

    if (password !== passwordConfirmation) {
      this.error = 'Passwords do not match';
      return;
    }

    console.log(email, password, passwordConfirmation);

    this.authService
      .registerWithEmailAndPass(email!, password!)
      .then(() => {
        this.router.navigateByUrl('home');
      })
      .catch((err) => {
        console.log(err);
      });
  }
}
