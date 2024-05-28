import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TokenService } from '../TokenService/token.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth/signin';
  private apiUrlSignup = 'http://localhost:8080/api/auth/signup';

  constructor(private http: HttpClient, private tokenService: TokenService, private router: Router) { }

  login(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, user).pipe(
      map(response => {
        if (response && response.token) {
          this.tokenService.setUserDetails(response.token, response.roles);
          return response;
        } else {
          throw new Error('Invalid login response');
        }
      }),
      catchError(error => {
        console.error('Login error:', error);
        throw error;
      })
    );
  }

  isAuthenticated(): boolean {
    const token = this.tokenService.getToken();
    return token !== null;
  }

  hasRole(role: string): boolean {
    const roles = this.tokenService.getRoles();
    return roles ? roles.includes(role) : false;
  }

  logout(): void {
    this.tokenService.removeUserDetails();
    this.router.navigate(['/']);
  }

  signup(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrlSignup, user).pipe(
      catchError(error => {
        console.error('Signup error:', error);
        throw error;
      })
    );
  }
}
