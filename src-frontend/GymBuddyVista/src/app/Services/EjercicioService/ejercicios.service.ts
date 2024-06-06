import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ejercicios } from 'src/app/Models/Ejercicios';

@Injectable({
  providedIn: 'root'
})
export class EjerciciosService {
  private baseUrl = 'http://localhost:8080/api/ejercicios'; // Añade el protocolo http://
  constructor(private http: HttpClient) {}

  getEjercicios(): Observable<Ejercicios[]> {
    return this.http.get<Ejercicios[]>(this.baseUrl);
  }

  getEjercicioById(id: number): Observable<Ejercicios> {
    return this.http.get<Ejercicios>(`${this.baseUrl}/${id}`);
  }

  createEjercicio(entrenamiento: Ejercicios): Observable<Ejercicios> {
    return this.http.post<Ejercicios>(this.baseUrl, entrenamiento);
  }

  updateEjercicio(entrenamiento: Ejercicios, id: number): Observable<Ejercicios> {
    return this.http.put<Ejercicios>(`${this.baseUrl}/${id}`, entrenamiento);
  }

  deleteEjercicio(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
