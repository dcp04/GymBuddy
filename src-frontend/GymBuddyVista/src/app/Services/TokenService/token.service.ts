import { Injectable } from '@angular/core';
import { JwtPayload, jwtDecode } from 'jwt-decode';

/**
 * A service to handle JWT tokens and user roles.
 *
 * @Injectable({ providedIn: 'root' })
 * @export
 * @class TokenService
 */
@Injectable({
  providedIn: 'root',
})
export class TokenService {
  constructor() {}

  /**
   * Retrieves the JWT token from session storage.
   *
   * @returns {string | null} The JWT token or null if not found.
   * @memberof TokenService
   */
  getToken(): string | null {
    return sessionStorage.getItem('token');
  }

  /**
   * Stores the JWT token and user roles in session storage.
   *
   * @param {string} token The JWT token.
   * @param {string[]} roles The user roles.
   * @memberof TokenService
   */
  setUserDetails(token: string, roles: string[]): void {
    sessionStorage.setItem('token', token);
    sessionStorage.setItem('roles', JSON.stringify(roles));
  }

  /**
   * Retrieves the user roles from session storage.
   *
   * @returns {string[] | null} The user roles or null if not found.
   * @memberof TokenService
   */
  getRoles(): string[] | null {
    const roles = sessionStorage.getItem('roles');
    return roles ? JSON.parse(roles) : null;
  }

  /**
   * Removes the JWT token and user roles from session storage.
   *
   * @memberof TokenService
   */
  removeUserDetails(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('roles');
  }

  /**
   * Decodes the JWT token and returns its payload.
   *
   * @param {string} token The JWT token.
   * @returns {JwtPayload | null} The JWT payload or null if decoding fails.
   * @memberof TokenService
   */
  decodeToken(token: string): JwtPayload | null {
    try {
      return jwtDecode<JwtPayload>(token);
    } catch (error) {
      console.error('Error al decodificar el token:', error);
      return null;
    }
  }
}
