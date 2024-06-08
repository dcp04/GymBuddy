import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
} from '@angular/router';
import { TokenService } from '../Services/TokenService/token.service';

/**
 * A service for guarding routes based on user roles.
 * Implements the CanActivate interface to control access to routes.
 */
@Injectable({
  providedIn: 'root',
})
export class RoleGuard implements CanActivate {
  /**
   * Constructs a new instance of the RoleGuard.
   *
   * @param tokenService - The TokenService for retrieving user roles.
   * @param router - The Router for navigating to different routes.
   */
  constructor(private tokenService: TokenService, private router: Router) {}

  /**
   * Checks if the user is authenticated and has the required role before allowing access to a route.
   * If the user is authenticated and has the required role, the function returns true.
   * If the user is not authenticated or does not have the required role, the function redirects the user to a page
   * and returns false.
   *
   * @param next - The ActivatedRouteSnapshot for the next route.
   * @param state - The RouterStateSnapshot for the current route state.
   *
   * @return True if the user is authenticated and has the required role, false otherwise.
   */
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    // Retrieve the user's roles using the method in TokenService
    const roles = this.tokenService.getRoles();
    // Retrieve the expected roles from the route data
    const expectedRoles = next.data['expectedRoles'] as string[];

    // Check if the user is authenticated and has at least one of the required roles
    if (!roles || !this.hasAnyRole(roles, expectedRoles)) {
      // If the user does not have the required role, redirect them to an unauthorized page or the home page
      this.router.navigate(['/news']);
      return false;
    }

    return true;
  }

  /**
   * A method to check if the user has at least one of the specified roles.
   *
   * @param roles - The user's roles.
   * @param expectedRoles - The array of required roles.
   *
   * @return True if the user has at least one of the specified roles, false otherwise.
   */
  private hasAnyRole(roles: string[] | null, expectedRoles: string[]): boolean {
    if (!roles) {
      return false;
    }
    return expectedRoles.some(role => roles.includes(role));
  }
}
