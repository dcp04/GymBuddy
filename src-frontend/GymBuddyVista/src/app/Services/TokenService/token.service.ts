import { Injectable } from '@angular/core';
import { JwtPayload, jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() { }
  getToken(): string | null {
    return sessionStorage.getItem('token');
  }

  //receives token and an array of roles
  setUserDetails(token: string, roles: string[]): void {
    //and store it in a session
    sessionStorage.setItem('token', token);
    sessionStorage.setItem('roles', JSON.stringify(roles));
  }

  //it gets all roles stored in session
  getRoles(): string[] | null {
    //gets roles
    const roles = sessionStorage.getItem('roles');
    //parse and return them as an array
    return roles ? JSON.parse(roles) : null;
  }

  //deletes token and roles
  removeUserDetails(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('roles');
  }

  decodeToken(token: string): JwtPayload | null {
    try {
      // Decodificar el token y devolver la carga útil (payload)
      return jwtDecode<JwtPayload>(token);
    } catch (error) {
      // Manejar cualquier error de decodificación de token
      console.error('Error al decodificar el token:', error);
      return null;
    }
  }
}
