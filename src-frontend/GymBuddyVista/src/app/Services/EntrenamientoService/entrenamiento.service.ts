import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Entrenamientos } from '../../Models/Entrenamientos';
import { Ejercicios } from 'src/app/Models/Ejercicios';

@Injectable({
  providedIn: 'root',
})
export class EntrenamientoService {
  private baseUrl = 'http://localhost:8080/api/entrenamientos';
  constructor(private http: HttpClient) {}

  getEntrenamientos(): Observable<Entrenamientos[]> {
    return this.http.get<Entrenamientos[]>(this.baseUrl);
  }

   getEntrenamientoById(id: number): Observable<Entrenamientos> {
    return this.http.get<Entrenamientos>(`${this.baseUrl}/${id}`);
  }

  getEjerciciosByEntrenamientoId(id: number): Observable<Ejercicios[]>{
    return this.http.get<Ejercicios[]>(`${this.baseUrl}/ejercicios/${id}`);
  }

  createEntrenamiento(
    entrenamiento: Entrenamientos
  ): Observable<Entrenamientos> {
    return this.http.post<Entrenamientos>(this.baseUrl, entrenamiento);
  }

  updateEntrenamiento(
    entrenamiento: Entrenamientos,
    id: number
  ): Observable<Entrenamientos> {
    return this.http.put<Entrenamientos>(`${this.baseUrl}${id}`, entrenamiento);
  }

  deleteEntrenamiento(id: number): Observable<void> {
    return this.http.delete<any>(`${this.baseUrl}${id}`);
  }
}
