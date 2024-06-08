import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/Models/user';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private baseUrl = 'http://localhost:8080/api/usuarios';
  private emailUrl = 'http://localhost:8080/api/usuarios/email';
  constructor(private http: HttpClient) {}

  getUsuarios(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }

  getUsuarioById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }

  getUsuarioByEmail(email: string): Observable<User> {
    return this.http.get<User>(`${this.emailUrl}/${email}`);
  }

  createUsuario(usuario: User): Observable<User> {
    return this.http.post<User>(this.baseUrl, usuario);
  }

  updateUsuario(id: number, usuario: User): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}/${id}`, usuario);
  }

  deleteUsuario(id: number): Observable<void> {
    return this.http.delete<any>(`${this.baseUrl}/${id}`);
  }
}
