export interface User {
    id: number;
    email: string;
    password: string;
    nombre: string;
    apellidos: string;
    estatura: number;
    peso: number;
    roles: Role;
}

export enum Role {
    USER = 'ROL_USER',
    ADMIN = 'ROL_ADMIN',
    ENTRENADOR = 'ROL_ENTRENADOR',
}

//Comprobar entidad DTO back