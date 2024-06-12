import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Ejercicios } from 'src/app/Models/Ejercicios';
import { Dificultad } from 'src/app/Models/Entrenamientos';
import { User } from 'src/app/Models/user';
import { EjerciciosService } from 'src/app/Services/EjercicioService/ejercicios.service';
import { EntrenamientoService } from 'src/app/Services/EntrenamientoService/entrenamiento.service';
import { UsuarioService } from 'src/app/Services/UsuarioService/usuario.service';

@Component({
  selector: 'app-nuevo-ejercicio',
  templateUrl: './nuevo-ejercicio.component.html',
  styleUrls: ['./nuevo-ejercicio.component.css'],
})
export class NuevoEjercicioComponent {
  ejercicioForm: FormGroup;
  ejercicios: Ejercicios[] = [];
  usuarios: User[] = [];
  selectedFile: File | null = null;

  constructor(
    private fb: FormBuilder,
    private ejercicioService: EjerciciosService,
    private usuarioService: UsuarioService,
    private router: Router
  ) {
    this.ejercicioForm = this.fb.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      creador: [null, Validators.required],
      imagenUrl: [''],
    });
  }

  ngOnInit(): void {
    this.usuarioService.getUsuarios().subscribe((data: User[]) => {
      this.usuarios = data;
    });
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  onSubmit(): void {
    if (this.ejercicioForm.valid) {
      const formData = new FormData();
      formData.append('nombre', this.ejercicioForm.get('nombre')?.value);
      formData.append(
        'descripcion',
        this.ejercicioForm.get('descripcion')?.value
      );
      formData.append('creador', this.ejercicioForm.get('creador')?.value);
      if (this.selectedFile) {
        formData.append('imagenUrl', this.selectedFile, this.selectedFile.name);
      }

      this.ejercicioService.createEjercicio(formData).subscribe(
        (response) => {
          console.log('Nuevo ejercicio creado:', response);
          this.router.navigate(['/']);
        },
        (error) => {
          console.error('Error al crear el ejercicio:', error);
        }
      );
    }
  }
}
