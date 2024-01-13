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

@Component({
  selector: 'login',
  standalone: true,
  imports: [
    CommonModule,
    HlmButtonDirective,
    HlmInputDirective,
    RouterLink,
    ReactiveFormsModule,
  ],
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {
  error = '';
  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
    verifyPassword: new FormControl('', [Validators.required]),
  });

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.loginForm;
  }

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

  signInWithEmailAndPass() {
    const email = this.loginForm.get('email')?.value;
    const password = this.loginForm.get('password')?.value;

    if (email === '' || password === '') {
      this.error = 'Please fill out all fields';
      return;
    }

    console.log(email, password);

    this.authService
      .signInWithEmailAndPass(email!, password!)
      .then(() => {
        
        this.router.navigateByUrl('home');
      })
      .catch((err) => {
        console.log(err);
      });
  }
}
