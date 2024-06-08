import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS, provideHttpClient, } from '@angular/common/http';
import { HeaderComponent } from './Components/Layout/header/header.component';
import { ButtonsComponent } from './Components/buttons/buttons.component';
import { FooterComponent } from './Components/Layout/footer/footer.component';
import { LoginComponent } from './Pages/User/login/login.component';
import { RegisterComponent } from './Pages/User/register/register.component';
import { ListaEntrenamientosComponent } from './Pages/User/lista-entrenamientos/lista-entrenamientos.component';
import { ListaEjerciciosComponent } from './Pages/User/lista-ejercicios/lista-ejercicios.component';
import { HomeComponent } from './Pages/User/home/home.component';
import { NuevoEntrenamientoComponent } from './Pages/Admin/nuevo-entrenamiento/nuevo-entrenamiento.component';
import { NuevoEjercicioComponent } from './Pages/Admin/nuevo-ejercicio/nuevo-ejercicio.component';
import { ListaUsuarioComponent } from './Pages/Admin/lista-usuario/lista-usuario.component';
import { AuthInterceptor } from './Interceptors/AuthInterceptor';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ButtonsComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    ListaEntrenamientosComponent,
    ListaEjerciciosComponent,
    HomeComponent,
    NuevoEntrenamientoComponent,
    NuevoEjercicioComponent,
    ListaUsuarioComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    provideHttpClient(),
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
