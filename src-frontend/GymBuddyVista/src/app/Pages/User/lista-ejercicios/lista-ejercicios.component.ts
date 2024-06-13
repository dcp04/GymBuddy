import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Ejercicios } from 'src/app/Models/Ejercicios';
import { EjerciciosService } from 'src/app/Services/EjercicioService/ejercicios.service';

@Component({
  selector: 'app-lista-ejercicios',
  templateUrl: './lista-ejercicios.component.html',
  styleUrls: ['./lista-ejercicios.component.css'],
})
export class ListaEjerciciosComponent {
  ejercicio: Ejercicios | undefined;
  id: number = 0;
  ejercicioDelete: number | null = null;
  isMenuDeleteOpen: boolean = false;
  type:string ='';

  constructor(
    private ejercicioService: EjerciciosService,
    private _route: ActivatedRoute,
    private route: Router
  ) {}

  ngOnInit() {
    this._route.params.subscribe((params) => {
      this.id = params['id'];
      this.showEjercicios(this.id);
    });
    if (localStorage.getItem('roles') === '["ROL_ADMIN"]' || localStorage.getItem('roles') === '["ROL_ENTRENADOR"]') {
      this.type = 'admin'
    }
  }

  showEjercicios(id: number) {
    this.ejercicioService.getEjercicioById(this.id).subscribe({
      next: (res) => {
        this.ejercicio = res;
        console.log(this.ejercicio);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  modalDelete(id: number | null): void {
    this.ejercicioDelete = id;
    this.isMenuDeleteOpen = !this.isMenuDeleteOpen;
  }

  deleteEjercicio(id: number | null): void {
    if (id !== null) {
      this.ejercicioService.deleteEjercicio(id).subscribe(() => {
        this.route.navigate(["/"]);
        this.isMenuDeleteOpen = false;
      });
    }
  }
}
