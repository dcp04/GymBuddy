import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
  constructor(
    private ejercicioService: EjerciciosService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.id = params['id'];
      this.showEjercicios(this.id);
    });
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
}
