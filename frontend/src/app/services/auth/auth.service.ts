import { Injectable, inject } from '@angular/core';
import {
  Auth,
  authState,
  createUserWithEmailAndPassword,
  signInWithEmailAndPassword,
  signInWithPopup,
  signOut,
} from '@angular/fire/auth';
import { GoogleAuthProvider } from 'firebase/auth';
import { ApiService } from '../api/api.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authFirebase = inject(Auth);
  authState$ = authState(this.authFirebase);

  constructor(private apiService: ApiService) {
    this.authState$.subscribe((user) => {
      if (user) {
        this.apiService
          .addUser(user.uid, user.photoURL || '')
          .subscribe((res) => {
            this.apiService.hasUser(user.uid).subscribe((res) => {
              console.log(res, user.uid, user.photoURL);
            });
          });
      }
    });
  }

  signInWithGoogle() {
    return signInWithPopup(
      this.authFirebase,
      new GoogleAuthProvider().setCustomParameters({ prompt: 'select_account' })
    );
  }

  registerWithEmailAndPass(email: string, password: string) {
    return createUserWithEmailAndPassword(this.authFirebase, email, password);
  }

  signInWithEmailAndPass(email: string, password: string) {
    return signInWithEmailAndPassword(this.authFirebase, email, password);
  }

  signOut() {
    return signOut(this.authFirebase);
  }
}
