export enum Rol {
    ROL_USER = 'ROL_USER',
    ROL_ADMIN = 'ROL_ADMIN',
    ROL_ENTRENADOR = 'ROL_ENTRENADOR',
  }
  
  export interface User {
    id: number;
    email: string;
    password: string;
    nombre: string;
    apellidos: string;
    estatura: number;
    peso: number;
    roles: Set<Rol>;
  }