import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, tap } from 'rxjs';
import { Entrenamientos } from '../../Models/Entrenamientos';
import { Ejercicios } from 'src/app/Models/Ejercicios';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EntrenamientoService {
  private baseUrl = environment.apiUrl + '/entrenamientos';
  private _refreshEntrenamientos$ = new Subject<void>();

  constructor(private http: HttpClient) {}

  get refresh$() {
    return this._refreshEntrenamientos$;
  }

  getEntrenamientos(): Observable<Entrenamientos[]> {
    return this.http.get<Entrenamientos[]>(this.baseUrl);
  }

  getEntrenamientoById(id: number): Observable<Entrenamientos> {
    return this.http.get<Entrenamientos>(`${this.baseUrl}/${id}`);
  }

  getEjerciciosByEntrenamientoId(id: number): Observable<Ejercicios[]> {
    return this.http.get<Ejercicios[]>(`${this.baseUrl}/ejercicios/${id}`);
  }

  createEntrenamiento(entrenamiento: FormData): Observable<Entrenamientos> {
    return this.http.post<Entrenamientos>(`${this.baseUrl}`, entrenamiento);
  }

  updateEntrenamiento(
    entrenamiento: Entrenamientos,
    id: number
  ): Observable<Entrenamientos> {
    return this.http.put<Entrenamientos>(`${this.baseUrl}${id}`, entrenamiento);
  }

  deleteEntrenamiento(id: number): Observable<void> {
    return this.http.delete<any>(`${this.baseUrl}/${id}`);
  }

  apuntarseAEntrenamiento(
    entrenamientoId: number,
    usuarioId: number
  ): Observable<any> {
    const url = `${this.baseUrl}/${entrenamientoId}/apuntarse?usuarioId=${usuarioId}`;
    return this.http.post(url, {}).pipe(
      tap(() => {
        this._refreshEntrenamientos$.next();
      })
    );
  }

  desapuntarseAEntrenamiento(
    entrenamientoId: number,
    usuarioId: number
  ): Observable<any> {
    const url = `${this.baseUrl}/${entrenamientoId}/desapuntarse?usuarioId=${usuarioId}`;
    return this.http.post(url, {}).pipe(
      tap(() => {
        this._refreshEntrenamientos$.next();
      })
    );
  }

  isUsuarioApuntado(
    entrenamientoId: number,
    usuarioId: number
  ): Observable<boolean> {
    const url = `${this.baseUrl}/${entrenamientoId}/isApuntado?usuarioId=${usuarioId}`;
    return this.http.get<boolean>(url);
  }

  getEntrenamientosApuntados(id: number): Observable<Entrenamientos[]> {
    return this.http.get<Entrenamientos[]>(`${this.baseUrl}/usuario/${id}`);
  }
}
