import { Component, OnInit } from '@angular/core';
import { Ejercicios } from 'src/app/Models/Ejercicios';
import { Entrenamientos } from 'src/app/Models/Entrenamientos';
import { EjerciciosService } from 'src/app/Services/EjercicioService/ejercicios.service';
import { EntrenamientoService } from 'src/app/Services/EntrenamientoService/entrenamiento.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  ejercicios: Ejercicios[] = [];
  entrenamientos: Entrenamientos[] = [];

  constructor(private ejercicioService: EjerciciosService, private entrenamientoService: EntrenamientoService) {}

  ngOnInit() {
    this.showEjercicios();
    this.showEntrenamientos();
  }

  showEjercicios() {
    this.ejercicioService.getEjercicios().subscribe({
      next: (res) => {
        this.ejercicios = res;
        this.ejercicios.sort((a, b) => a.id - b.id);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  showEntrenamientos() {
    this.entrenamientoService.getEntrenamientos().subscribe({
      next: (res) => {
        this.entrenamientos = res;
        this.entrenamientos.sort((a, b) => a.id - b.id);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
}
