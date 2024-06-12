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
   * Retrieves the JWT token from local storage.
   *
   * @returns {string | null} The JWT token or null if not found.
   * @memberof TokenService
   */
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  /**
   * Stores the JWT token and user roles in local storage.
   *
   * @param {string} token The JWT token.
   * @param {string[]} roles The user roles.
   * @memberof TokenService
   */
  setUserDetails(token: string, roles: string[]): void {
    localStorage.setItem('token', token);
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  /**
   * Retrieves the user roles from local storage.
   *
   * @returns {string[] | null} The user roles or null if not found.
   * @memberof TokenService
   */
  getRoles(): string[] | null {
    const roles = localStorage.getItem('roles');
    return roles ? JSON.parse(roles) : null;
  }

  /**
   * Removes the JWT token and user roles from local storage.
   *
   * @memberof TokenService
   */
  removeUserDetails(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('roles');
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
