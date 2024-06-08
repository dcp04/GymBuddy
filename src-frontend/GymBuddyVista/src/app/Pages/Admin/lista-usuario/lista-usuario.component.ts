import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Role, User } from 'src/app/Models/user';
import { AuthService } from 'src/app/Services/AuthService/auth.service';
import { TokenService } from 'src/app/Services/TokenService/token.service';
import { UsuarioService } from 'src/app/Services/UsuarioService/usuario.service';

@Component({
  selector: 'app-lista-usuario',
  templateUrl: './lista-usuario.component.html',
  styleUrls: ['./lista-usuario.component.css'],
})
export class ListaUsuarioComponent {
  users: User[] = [];
  usertoUpdate: User = {
    id: 0,
    email: '',
    password: '',
    nombre: '',
    apellidos: '',
    estatura: 0,
    peso: 0,
    roles: Role.USER,
  };
  selectedUserIdToDelete: number | null = null;
  selectedUserEmailToDelete: string | null = null;
  selectedUserIdToUpdate: number | null = null;
  isMenuOpen: boolean = false;
  isMenuDeleteOpen: boolean = false;
  tokenData: any = null;
  email: string = '';
  formUser: FormGroup;

  /**
   * Constructor for the UsersListComponent.
   * Initializes the necessary services, form builder, and form group for user data.
   *
   * @param usuarioService - Service for managing user data.
   * @param router - Service for navigation.
   * @param tokenService - Service for handling token-based authentication.
   * @param authService - Service for managing authentication state.
   * @param configService - Service for loading and managing configuration data.
   * @param fb - Form builder for creating form groups.
   */
  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private tokenService: TokenService,
    private authService: AuthService,
    private fb: FormBuilder
  ) {
    /**
     * Reactive form group for user data.
     * Includes fields for DAS, first name, last name, and role.
     * DAS is required and must not be empty.
     * First name and last name are required, must not be empty, and must only contain alphabetic characters, spaces, and hyphens.
     * Role is required and must not be empty.
     */
    this.formUser = this.fb.group({
      email: ['', [Validators.required]],
      nombre: [
        '',
        [
          Validators.required,
          Validators.pattern(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s-]+$/),
          Validators.maxLength(25),
        ],
      ],
      apellidos: [
        '',
        [
          Validators.required,
          Validators.pattern(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s-]+$/),
          Validators.maxLength(25),
        ],
      ],
      roles: ['', Validators.required],
    });
  }

  /**
   * Initializes the component and performs necessary operations.
   */
  ngOnInit() {
    const token = this.tokenService.getToken();
    if (token) {
      const tokenData = this.tokenService.decodeToken(token);
      this.email = tokenData?.sub || '';
    }
    this.showUsers();
  }

  /**
   * This lifecycle hook is called after Angular has fully initialized all data-bound properties
   * in a directive/component. It's a good place to perform complex initializations that require
   * that the data-bound properties have been set.
   */
  ngAfterViewInit() {
    this.showUsers();
  }

  /**
   * This lifecycle hook is called after Angular has fully initialized all data-bound properties
   * in a directive/component. It's a good place to perform complex initializations that require
   * that the data-bound properties have been set.
   */
  showUsers() {
    this.usuarioService.getUsuarios().subscribe({
      next: (users) => {
        this.users = users;
        this.users.sort((a, b) => a.id - b.id);
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  /**
   * Opens or closes the user menu.
   *
   * @remarks
   * This method toggles the `isMenuOpen` property, which controls the visibility of the user menu.
   * It is called when the user clicks on the menu button.
   *
   * @returns {void}
   */
  modal() {
    this.isMenuOpen = !this.isMenuOpen;
  }

  /**
   * Opens the user update modal and pre-fills the form with the selected user's data.
   *
   * @param id - The ID of the user to be updated.
   * @remarks
   * This method sets the `selectedUserIdToUpdate` to null, closes the user menu,
   * sets the `selectedUserIdToUpdate` to the provided ID, and then retrieves the user's data
   * from the server using the `UserService`. Once the user data is retrieved, it is assigned to
   * the `userToUpdate` property and the form is updated with the user's data using `patchValue`.
   * If an error occurs during the retrieval of user data, it is logged to the console.
   *
   * @returns {void}
   */
  modalUpdate(id: number | null) {
    this.selectedUserIdToUpdate = null;
    this.modal();
    this.selectedUserIdToUpdate = id;

    if (this.selectedUserIdToUpdate !== null) {
      this.usuarioService.getUsuarioById(this.selectedUserIdToUpdate).subscribe({
        next: (res) => {
          this.usertoUpdate = res;
          this.formUser.patchValue({
            email: this.usertoUpdate.email,
            firstName: this.usertoUpdate.nombre,
            lastName: this.usertoUpdate.apellidos,
            role: this.usertoUpdate.roles,
          });
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }

  /**
   * Updates the selected user with the provided form data.
   *
   * @remarks
   * This method checks if the `selectedUserIdToUpdate` is not null and if the form is valid.
   * If both conditions are met, it calls the `updateUser` method of the `UserService` with the selected user's ID and form data.
   * On successful update, it closes the user update modal, refreshes the list of users, and navigates to the '/users' route.
   * If an error occurs during the update process, it logs the error to the console.
   *
   * @returns {void}
   */
  updateUser() {
    if (this.selectedUserIdToUpdate !== null && this.formUser.valid) {
      this.usuarioService
        .updateUsuario(this.selectedUserIdToUpdate, this.formUser.value)
        .subscribe({
          next: (res) => {
            this.modal();
            this.showUsers();
            this.router.navigate(['/users']);
          },
          error: (err) => {
            console.log(err);
          },
        });
    }
  }

  /**
   * Updates the selected user with the provided form data.
   *
   * @remarks
   * This method checks if the `selectedUserIdToUpdate` is not null and if the form is valid.
   * If both conditions are met, it calls the `updateUser` method of the `UserService` with the selected user's ID and form data.
   * On successful update, it closes the user update modal, refreshes the list of users, and navigates to the '/users' route.
   * If an error occurs during the update process, it logs the error to the console.
   *
   * @returns {void}
   */
  modalDelete(id: number | null, email: string | null) {
    this.selectedUserIdToDelete = id;
    this.selectedUserEmailToDelete = email;
    if (id === null) {
      this.selectedUserIdToDelete = null;
    }
    this.isMenuDeleteOpen = !this.isMenuDeleteOpen;
  }

  /**
   * Deactivates the user with the given ID.
   * If the deactivated user is the currently logged-in user, logs them out.
   *
   * @param selectedUserIdToDelete - The ID of the user to deactivate.
   * @returns {void}
   */
  deactivateUser(selectedUserIdToDelete: number) {
    if (selectedUserIdToDelete !== null) {
      this.usuarioService.deleteUsuario(selectedUserIdToDelete).subscribe({
        next: (res) => {
          const token = this.tokenService.getToken();
          if (token) {
            this.tokenData = this.tokenService.decodeToken(token);
            this.email = this.tokenData ? this.tokenData.sub : null;
            if (this.email === this.selectedUserEmailToDelete) {
              this.authService.logout();
            }
          }
          this.isMenuDeleteOpen = !this.isMenuDeleteOpen;
          this.showUsers();
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }
}
