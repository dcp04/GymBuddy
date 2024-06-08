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

  /**
   * Performs user login.
   *
   * @param {any} user - User credentials.
   * @returns {Observable<any>} - Observable that emits the login response.
   */
  login(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, user).pipe(
      map((response) => {
        if (response && response.token) {
          this.tokenService.setUserDetails(response.token, response.roles);
          return response;
        } else {
          throw new Error('Invalid login response');
        }
      }),
      catchError((error) => {
        console.error('Login error:', error);
        throw error;
      })
    );
  }

  /**
   * Checks if the user is authenticated.
   *
   * @returns {boolean} - True if the user is authenticated, false otherwise.
   */
  isAuthenticated(): boolean {
    return !!this.tokenService.getToken();
  }

  /**
   * Checks if the user has a specific role.
   *
   * @param {string} role - Role to check.
   * @returns {boolean} - True if the user has the role, false otherwise.
   */
  hasRole(role: string): boolean {
    const roles = this.tokenService.getRoles();
    return roles ? roles.includes(role) : false;
  }

  /**
   * Performs user logout.
   */
  logout(): void {
    this.tokenService.removeUserDetails();
    this.router.navigate(['/']);
  }

  /**
   * Performs user signup.
   *
   * @param {any} user - User details.
   * @returns {Observable<any>} - Observable that emits the signup response.
   */
  signup(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrlSignup, user).pipe(
      catchError((error) => {
        console.error('Signup error:', error);
        throw error;
      })
    );
  }
}
