import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../Services/AuthService/auth.service';

/**
 * A service for guarding routes and ensuring user authentication.
 * Implements the CanActivate interface to control access to routes.
 */
@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  /**
   * Constructs a new instance of the AuthGuard.
   *
   * @param authService - The AuthService for checking user authentication.
   * @param router - The Router for navigating to different routes.
   */
  constructor(private authService: AuthService, private router: Router) {}

  /**
   * Checks if the user is authenticated before allowing access to a route.
   * If the user is authenticated, the function returns true.
   * If the user is not authenticated, the function redirects the user to the login page and returns false.
   *
   * @return True if the user is authenticated, false otherwise.
   */
  canActivate(): boolean {
    if (this.authService.isAuthenticated()) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}
