import { Component, ElementRef, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Ejercicios } from 'src/app/Models/Ejercicios';
import { Dificultad, Entrenamientos } from 'src/app/Models/Entrenamientos';
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
  /**
   * News item being created.
   */
  entrenamiento: Entrenamientos = {
    id: 0,
    nombre: '',
    dificultad: null,
    ejercicios: [],
    creador: null,
    imagenUrl: '',
  };
  ejercicios: Ejercicios[] = [];
  dificultades = Object.values(Dificultad);
  id: number = 0;
  tokenData: any = null;

  selectedFileName: string = 'No file selected';
  previewUrl: any = null;
  NombreHasError: boolean = false;
  EjerciciosHasError: boolean = false;
  DificultadHasError: boolean = false;
  NombreLengthError: boolean = false;
  fileError: string = '';

  minResolutionWidth = 800;
  minResolutionHeight = 600;
  NombreError: boolean = false;
  EjerciciosError: boolean = false;
  DificultadError: boolean = false;

  @ViewChild('fileInput', { static: false })
  fileInput!: ElementRef<HTMLInputElement>;
  NewImg: string = '';

  selectedEjercicio: number | null = null; // Agregado para manejar la selección de ejercicio

  /**
   * Constructor for the AddNewComponent.
   *
   * @param entrenamientoService - Service for creating news items.
   * @param logService - Service for creating log entries.
   * @param router - Router for navigation.
   * @param a_router - Activated route for getting route parameters.
   * @param userservice - Service for getting user information.
   * @param tokenService - Service for handling tokens.
   * @param configService - Service for loading configuration settings.
   */
  constructor(
    private entrenamientoService: EntrenamientoService,
    private ejercicioService: EjerciciosService,
    private router: Router,
    private a_router: ActivatedRoute,
    private usuarioService: UsuarioService,
    private tokenService: TokenService
  ) {}

  /**
   * Method to handle file selection for the news item.
   *
   * @param event - Event object containing the selected files.
   */
  onFileSelected(event: any): void {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const image = new Image();
        image.onload = () => {
          const width = image.width;
          const height = image.height;
          if (
            width < this.minResolutionWidth ||
            height < this.minResolutionHeight
          ) {
            this.fileError = `The resolution of the file must have a minimum of ${this.minResolutionWidth}x${this.minResolutionHeight} pixels.`;
            this.previewUrl = null;
            this.entrenamiento.imagenUrl = null;
            this.selectedFileName = 'No file selected';
          } else {
            this.previewUrl = e.target.result;
            this.entrenamiento.imagenUrl = file;
            this.selectedFileName = file.name;
            this.fileError = '';
          }
        };
        image.src = e.target.result;
      };
      reader.readAsDataURL(file);
    }

    this.validateForm();
  }

  /**
   * Method to initialize the component.
   */
  ngOnInit() {
    const token = this.tokenService.getToken();
    if (token) {
      this.tokenData = this.tokenService.decodeToken(token);
      this.id = this.tokenData ? this.tokenData.sub : null;
    }
    this.getUser();
    this.getEjercicios();
  }

  /**
   * Method to get the user associated with the current session.
   */
  getUser() {
    this.a_router.params.subscribe(() => {});
    this.usuarioService.getUsuarioById(this.id).subscribe({
      next: (res) => {
        this.entrenamiento.creador = res;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  getEjercicios(){
    this.ejercicioService.getEjercicios().subscribe({
      next: (res) => {
        this.ejercicios = res;
      },
      error: (err) => {
        console.log(err);
      },
    })
  }

  /**
   * Method to validate the form inputs.
   */
  validateForm(): void {
    if (
      (this.NombreHasError =
        this.entrenamiento.nombre.length === 0 ||
        !/^[a-zA-ZáéíóúñÑ\s'"!¡¿?,%€]+$/.test(this.entrenamiento.nombre))
    ) {
      this.NombreError = true;
    }
    if (
      (this.EjerciciosHasError = this.entrenamiento.ejercicios.length === 0)
    ) {
      this.EjerciciosError = true;
    }
    if ((this.DificultadHasError = this.entrenamiento.dificultad === null)) {
      this.DificultadError = true;
    }
  }

  /**
   * Method to check if the form is fully completed.
   *
   * @returns True if the form is fully completed, false otherwise.
   */
  isFormFullyCompleted(): boolean {
    return (
      !this.NombreHasError &&
      !this.NombreLengthError &&
      !this.DificultadHasError &&
      !this.EjerciciosHasError &&
      this.entrenamiento.imagenUrl !== null
    );
  }

  /**
   * Method to create a new news item with a file.
   *
   * @param form - Form object containing the news item data.
   */
  createNewsWithFile(form: NgForm): void {
    this.validateForm();
    if (form.valid && this.isFormFullyCompleted()) {
      this.entrenamientoService
        .createEntrenamiento(this.entrenamiento)
        .subscribe({
          next: (news) => {
            this.router.navigate(['/news']);
            form.resetForm();
          },
          error: (error) => console.error('Error:', error),
        });
    } else {
      console.error('Form is not valid or file is not uploaded.');
    }
  }

  
  onEjercicioSelected(event: Event): void {
    const target = event.target as HTMLSelectElement;
    const ejercicioId = Number(target.value);
    const ejercicio = this.ejercicios.find(e => e.id === ejercicioId);
    if (ejercicio && !this.entrenamiento.ejercicios.includes(ejercicio)) {
      this.entrenamiento.ejercicios.push(ejercicio);
    }
  }
}
