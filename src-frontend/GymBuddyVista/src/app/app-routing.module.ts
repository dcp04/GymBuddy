import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Pages/User/login/login.component';
import { RegisterComponent } from './Pages/User/register/register.component';
import { ListaEntrenamientosComponent } from './Pages/User/lista-entrenamientos/lista-entrenamientos.component';
import { ListaEjerciciosComponent } from './Pages/User/lista-ejercicios/lista-ejercicios.component';
import { HomeComponent } from './Pages/User/home/home.component';
import { NuevoEntrenamientoComponent } from './Pages/Admin/nuevo-entrenamiento/nuevo-entrenamiento.component';
import { NuevoEjercicioComponent } from './Pages/Admin/nuevo-ejercicio/nuevo-ejercicio.component';
import { ListaUsuarioComponent } from './Pages/Admin/lista-usuario/lista-usuario.component';
import { AuthGuard } from './Guards/auth.guard';
import { RoleGuard } from './Guards/role.guard';
import { EntrenamientosApuntadoComponent } from './Pages/User/entrenamientos-apuntado/entrenamientos-apuntado.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'entrenamientos/:id', component: ListaEntrenamientosComponent},
  {path: 'entrenamientosApuntados', component: EntrenamientosApuntadoComponent},
  {path: 'ejercicios/:id', component: ListaEjerciciosComponent},
  {path: 'nuevo_entrenamiento', component: NuevoEntrenamientoComponent, canActivate: [AuthGuard, RoleGuard], data: { expectedRoles: ['ROL_ADMIN', 'ROL_ENTRENADOR'] }},
  {path: 'nuevo_ejercicio', component: NuevoEjercicioComponent, canActivate: [AuthGuard, RoleGuard], data: { expectedRoles: ['ROL_ADMIN', 'ROL_ENTRENADOR'] }},
  {path: 'usuarios', component: ListaUsuarioComponent, canActivate: [AuthGuard, RoleGuard], data: { expectedRoles: ['ROL_ADMIN'] }},
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
