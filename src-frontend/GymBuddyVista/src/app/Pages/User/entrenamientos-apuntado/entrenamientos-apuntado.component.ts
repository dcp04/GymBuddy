import { Component } from '@angular/core';
import { Entrenamientos } from 'src/app/Models/Entrenamientos';
import { User } from 'src/app/Models/user';
import { EntrenamientoService } from 'src/app/Services/EntrenamientoService/entrenamiento.service';
import { TokenService } from 'src/app/Services/TokenService/token.service';
import { UsuarioService } from 'src/app/Services/UsuarioService/usuario.service';

@Component({
  selector: 'app-entrenamientos-apuntado',
  templateUrl: './entrenamientos-apuntado.component.html',
  styleUrls: ['./entrenamientos-apuntado.component.css']
})
export class EntrenamientosApuntadoComponent {
  entrenamientos: Entrenamientos[] = [];
  type: string = '';
  user: boolean = false;
  tokenData: any = null;
  email: string = '';
  usuario!: User;

  constructor(
    private tokenService: TokenService,
    private userService: UsuarioService,
    private entrenamientoService: EntrenamientoService
  ) {}

  ngOnInit() {
    if (
      localStorage.getItem('roles') === '["ROL_ADMIN"]' ||
      localStorage.getItem('roles') === '["ROL_ENTRENADOR"]'
    ) {
      this.type = 'admin';
    }
    if (localStorage.getItem('roles') === '["ROL_USER"]') {
      this.user = true;
    }
    const token = this.tokenService.getToken();
    if (token) {
      this.tokenData = this.tokenService.decodeToken(token);
      this.email = this.tokenData ? this.tokenData.sub : null;
      if (this.email) {
        this.userService.getUsuarioByEmail(this.email).subscribe({
          next: (res) => {
            this.usuario = res;
            console.log(this.usuario.id);
            this.showEntrenamientos();
          },
          error: (err) => {
            console.log('Error al obtener los datos del usuario:', err);
          },
        });
      }
    }
  }


  showEntrenamientos() {
    this.entrenamientoService.getEntrenamientosApuntados(this.usuario.id).subscribe({
      next: (res) => {
        this.entrenamientos = res;
        this.entrenamientos.sort((a, b) => a.id - b.id);
        if (this.usuario) {
          this.entrenamientos.forEach((entrenamiento) => {
          });
        }
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

}
