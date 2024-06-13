import { Component, OnInit } from '@angular/core';
import { Ejercicios } from 'src/app/Models/Ejercicios';
import { Entrenamientos } from 'src/app/Models/Entrenamientos';
import { User } from 'src/app/Models/user';
import { EjerciciosService } from 'src/app/Services/EjercicioService/ejercicios.service';
import { EntrenamientoService } from 'src/app/Services/EntrenamientoService/entrenamiento.service';
import { TokenService } from 'src/app/Services/TokenService/token.service';
import { UsuarioService } from 'src/app/Services/UsuarioService/usuario.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  ejercicios: Ejercicios[] = [];
  entrenamientos: Entrenamientos[] = [];
  type: string = '';
  user: boolean = false;
  tokenData: any = null;
  email: string = '';
  apuntado: boolean = false;
  usuario!: User;
  apuntados: { [key: number]: boolean } = {};

  constructor(
    private ejercicioService: EjerciciosService,
    private tokenService: TokenService,
    private userService: UsuarioService,
    private entrenamientoService: EntrenamientoService
  ) {}

  ngOnInit() {
    this.showEjercicios();
    this.showEntrenamientos();
    if (
      localStorage.getItem('roles') === '["ROL_ADMIN"]' ||
      localStorage.getItem('roles') === '["ROL_ENTRENADOR"]'
    ) {
      this.type = 'admin';
    }
    if (localStorage.getItem('roles') === '["ROL_USER"]') {
      this.user = true;

      const token = this.tokenService.getToken();
      if (token) {
        this.tokenData = this.tokenService.decodeToken(token);
        this.email = this.tokenData ? this.tokenData.sub : null;
        if (this.email) {
          this.userService.getUsuarioByEmail(this.email).subscribe({
            next: (res) => {
              this.usuario = res;
            },
            error: (err) => {
              console.log('Error al obtener los datos del usuario:', err);
            },
          });
        }
      }
      if (this.usuario) {
        this.entrenamientos.forEach((entrenamiento) => {
          this.checkApuntado(entrenamiento.id);
        });
      }
    }
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
        if (this.usuario) {
          this.entrenamientos.forEach((entrenamiento) => {
            this.checkApuntado(entrenamiento.id);
          });
        }
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  checkApuntado(entrenamientoId: number): void {
    if (this.usuario && this.usuario.id) {
      this.entrenamientoService
        .isUsuarioApuntado(entrenamientoId, this.usuario.id)
        .subscribe(
          (isApuntado) => {
            this.apuntados[entrenamientoId] = isApuntado;
          },
          (error) => {
            console.error('Error:', error);
          }
        );
    }
  }

  apuntarse(entrenamientoId: number): void {
    this.entrenamientoService
      .apuntarseAEntrenamiento(entrenamientoId, this.usuario.id)
      .subscribe(
        (response) => {
          console.log('Response:', response);
          this.apuntados[entrenamientoId] = true;
        },
        (error) => {
          this.apuntados[entrenamientoId] = true;
        }
      );
  }

  desapuntarse(entrenamientoId: number): void {
    this.entrenamientoService
      .desapuntarseAEntrenamiento(entrenamientoId, this.usuario.id)
      .subscribe(
        (response) => {
          console.log('Response:', response);
          this.apuntados[entrenamientoId] = false;
        },
        (error) => {
          this.apuntados[entrenamientoId] = false;
        }
      );
  }
}
