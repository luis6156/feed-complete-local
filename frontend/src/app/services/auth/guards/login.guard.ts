import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): Observable<boolean> {
    return this.authService.authState$.pipe(
      take(1),
      map(user => {
        if (user) {
          this.router.navigate(['/home']);
          return false;
        } else {
          return true;
        }
      })
    );
  }
}