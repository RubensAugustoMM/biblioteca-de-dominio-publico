import type { Entidade } from "./entidade";

export interface Usuario extends Entidade{
    login : string;
    nome : string;
    cpf : string;
    senha : string;
    email : string;
    idade : number;
    admin : boolean;
}
