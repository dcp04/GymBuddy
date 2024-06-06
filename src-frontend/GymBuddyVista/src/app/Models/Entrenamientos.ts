import { User } from './user';
import { Ejercicios } from './Ejercicios';

export interface Entrenamientos {
    id: number;
    nombre: string;
    dificultad: Dificultad | null;
    ejercicios: Ejercicios[];
    creador: User | null;
    imagenUrl: string | null;
}

export enum Dificultad {
    FACIL = 'FACIL',
    MODERADO = 'MODERADO',
    DIFICIL = 'DIFICIL',
}
