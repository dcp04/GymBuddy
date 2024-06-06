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
  setUserDetails(token: string, rol: string[]): void {
    //and store it in a session
    sessionStorage.setItem('token', token);
    sessionStorage.setItem('rol', JSON.stringify(rol));
  }

  //it gets all roles stored in session
  getRoles(): string[] | null {
    //gets roles
    const rol = sessionStorage.getItem('rol');
    //parse and return them as an array
    return rol ? JSON.parse(rol) : null;
  }

  //deletes token and roles
  removeUserDetails(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('rol');
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
