import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsuarioService } from './../../../Services/UsuarioService/usuario.service'; 
import { Rol, User } from 'src/app/Models/user';
import { TokenService } from 'src/app/Services/TokenService/token.service';

@Component({
  selector: 'app-lista-usuario',
  templateUrl: './lista-usuario.component.html',
  styleUrls: ['./lista-usuario.component.css'],
})
export class ListaUsuarioComponent implements OnInit {
  formUser: FormGroup;
  users: User[] = [];
  usertoUpdate: User | null = null;
  userDelete: number | null = null;
  emailDelete: string | null = null;
  isMenuOpen: boolean = false;
  isMenuDeleteOpen: boolean = false;
  email: string = '';
  roles = Object.values(Rol);

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private router: Router,
    private tokenService: TokenService
  ) {
    this.formUser = this.fb.group({
      id: [''],
      email: [''],
      nombre: [
        '',
        [
          Validators.required,
          Validators.pattern('^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]*$'),
          Validators.maxLength(25),
        ],
      ],
      apellidos: [
        '',
        [
          Validators.required,
          Validators.pattern('^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]*$'),
          Validators.maxLength(25),
        ],
      ],
      roles: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.loadUsers();
    const token = this.tokenService.getToken();
    if (token) {
      const tokenData = this.tokenService.decodeToken(token);
      this.email = tokenData?.sub || '';
    }
  }

  loadUsers() {
    this.usuarioService.getUsuarios().subscribe((data: User[]) => {
      this.users = data;
    });
  }

  modalUpdate(id: number): void {
    this.usuarioService.getUsuarioById(id).subscribe((data: User) => {
      this.usertoUpdate = data;
      this.formUser.patchValue({
        id: data.id,
        email: data.email,
        nombre: data.nombre,
        apellidos: data.apellidos,
        roles: data.roles,
      });
      this.isMenuOpen = true;
    });
  }

  modal(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  updateUser(): void {
    if (this.formUser.valid && this.usertoUpdate) {
      const id = this.formUser.value.id;
      const userData: User = {
        ...this.usertoUpdate,
        nombre: this.formUser.value.nombre,
        apellidos: this.formUser.value.apellidos,
        roles: this.formUser.value.roles,
      };
      this.usuarioService.updateUsuario(id, userData).subscribe(() => {
        this.loadUsers();
        this.isMenuOpen = false;
      });
    }
  }

  modalDelete(id: number | null, email: string | null): void {
    this.userDelete = id;
    this.emailDelete = email;
    this.isMenuDeleteOpen = !this.isMenuDeleteOpen;
  }

  deleteUser(id: number | null): void {
    if (id !== null) {
      this.usuarioService.deleteUsuario(id).subscribe(() => {
        this.loadUsers();
        this.isMenuDeleteOpen = false;
      });
    }
  }
}
 