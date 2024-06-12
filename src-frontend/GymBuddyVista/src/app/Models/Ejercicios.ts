import { Entrenamientos } from "./Entrenamientos";
import { User } from "./user";

export interface Ejercicios{
    id: number;
    nombre: string;
    descripcion: string;
    entrenamiento: Entrenamientos[];
    creador: User | null;
    imagenUrl: string | null;
}