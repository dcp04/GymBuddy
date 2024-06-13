import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EntrenamientoService } from 'src/app/Services/EntrenamientoService/entrenamiento.service';
import { Entrenamientos } from 'src/app/Models/Entrenamientos';
import { Ejercicios } from 'src/app/Models/Ejercicios';

@Component({
  selector: 'app-lista-entrenamientos',
  templateUrl: './lista-entrenamientos.component.html',
  styleUrls: ['./lista-entrenamientos.component.css'],
})
export class ListaEntrenamientosComponent implements OnInit {
  entrenamiento: Entrenamientos | undefined;
  ejercicios: Ejercicios[] = [];
  id: number = 0;
  entrenamientoDelete: number | null = null;
  isMenuDeleteOpen: boolean = false;
  type: string = '';
  
  constructor(
    private entrenamientoService: EntrenamientoService,
    private route: Router,
    private _route: ActivatedRoute,
  ) {}

  ngOnInit() {
    this._route.params.subscribe((params) => {
      this.id = params['id'];
      this.showEntrenamiento(this.id);
      this.showEjerciciosDeEntrenamiento();
    });
    if (localStorage.getItem('roles') === '["ROL_ADMIN"]' || localStorage.getItem('roles') === '["ROL_ENTRENADOR"]') {
      this.type = 'admin'
    }

  }

  showEntrenamiento(id: number) {
    this.entrenamientoService.getEntrenamientoById(this.id).subscribe({
      next: (res) => {
        this.entrenamiento = res;
        console.log(this.entrenamiento);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  showEjerciciosDeEntrenamiento() {
    this.entrenamientoService
      .getEjerciciosByEntrenamientoId(this.id)
      .subscribe({
        next: (res) => {
          this.ejercicios = res;
          console.log(this.ejercicios);
          this.ejercicios.sort((a, b) => a.id - b.id);
        },
        error: (err) => {
          console.log(err);
        },
      });
  }

  modalDelete(id: number | null): void {
    this.entrenamientoDelete = id;
    this.isMenuDeleteOpen = !this.isMenuDeleteOpen;
  }

  deleteEntrenamiento(id: number | null): void {
    if (id !== null) {
      this.entrenamientoService.deleteEntrenamiento(id).subscribe(() => {
        this.route.navigate(["/"]);
        this.isMenuDeleteOpen = false;
      });
    }
  }
}
