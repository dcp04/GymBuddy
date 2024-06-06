import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Pages/User/login/login.component';
import { RegisterComponent } from './Pages/User/register/register.component';
import { ListaEntrenamientosComponent } from './Pages/User/lista-entrenamientos/lista-entrenamientos.component';
import { ListaEjerciciosComponent } from './Pages/User/lista-ejercicios/lista-ejercicios.component';
import { HomeComponent } from './Pages/User/home/home.component';
import { NuevoEntrenamientoComponent } from './Pages/Admin/nuevo-entrenamiento/nuevo-entrenamiento.component';
import { NuevoEjercicioComponent } from './Pages/Admin/nuevo-ejercicio/nuevo-ejercicio.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'entrenamientos/:id', component: ListaEntrenamientosComponent},
  {path: 'ejercicios/:id', component: ListaEjerciciosComponent},
  {path: 'nuevo_entrenamiento', component: NuevoEntrenamientoComponent},
  {path: 'nuevo_ejercicio', component: NuevoEjercicioComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
