import type { Autor } from "./autor";
import type { Editora as Editora } from "./editora";
import type { Entidade } from "./entidade";

export interface Livro extends Entidade{
    nome : string;
    dataLancamento : Date;
    genero : string;
    idEditora : number;
    editora : Editora;
    idAutor : number;
    autor : Autor;
}