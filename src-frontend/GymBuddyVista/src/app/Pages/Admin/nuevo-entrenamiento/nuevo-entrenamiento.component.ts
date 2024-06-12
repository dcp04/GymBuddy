import { Component, ElementRef, ViewChild } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  NgForm,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Ejercicios } from 'src/app/Models/Ejercicios';
import { Dificultad, Entrenamientos } from 'src/app/Models/Entrenamientos';
import { User } from 'src/app/Models/user';
import { EjerciciosService } from 'src/app/Services/EjercicioService/ejercicios.service';
import { EntrenamientoService } from 'src/app/Services/EntrenamientoService/entrenamiento.service';
import { TokenService } from 'src/app/Services/TokenService/token.service';
import { UsuarioService } from 'src/app/Services/UsuarioService/usuario.service';

@Component({
  selector: 'app-nuevo-entrenamiento',
  templateUrl: './nuevo-entrenamiento.component.html',
  styleUrls: ['./nuevo-entrenamiento.component.css'],
})
export class NuevoEntrenamientoComponent {
  entrenamientoForm: FormGroup;
  dificultades = Object.values(Dificultad);
  ejercicios: Ejercicios[] = [];
  usuarios: User[] = [];
  selectedFile: File | null = null;

  constructor(
    private fb: FormBuilder,
    private entrenamientoService: EntrenamientoService,
    private ejercicioService: EjerciciosService,
    private usuarioService: UsuarioService,
    private router: Router
  ) {
    this.entrenamientoForm = this.fb.group({
      nombre: ['', Validators.required],
      dificultad: [null, Validators.required],
      ejercicios: [[], Validators.required],
      creador: [null, Validators.required],
      imagenUrl: [''],
    });
  }

  ngOnInit(): void {
    this.ejercicioService.getEjercicios().subscribe((data: Ejercicios[]) => {
      this.ejercicios = data;
    });

    this.usuarioService.getUsuarios().subscribe((data: User[]) => {
      this.usuarios = data;
    });
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  onSubmit(): void {
    if (this.entrenamientoForm.valid) {
      const formData = new FormData();
      formData.append('nombre', this.entrenamientoForm.get('nombre')?.value);
      formData.append(
        'dificultad',
        this.entrenamientoForm.get('dificultad')?.value
      );
      formData.append('creador', this.entrenamientoForm.get('creador')?.value);

      const ejerciciosSeleccionados =
        this.entrenamientoForm.get('ejercicios')?.value;
      ejerciciosSeleccionados.forEach((ejercicio: string, index: number) => {
        formData.append('ejercicios', ejercicio);
      });

      if (this.selectedFile) {
        formData.append('imagenUrl', this.selectedFile, this.selectedFile.name);
      }

      this.entrenamientoService.createEntrenamiento(formData).subscribe(
        (response) => {
          console.log('Nuevo entrenamiento creado:', response);
          this.router.navigate(['/']);
        },
        (error) => {
          console.error('Error al crear el entrenamiento:', error);
        }
      );
    }
  }
}
