import { type Autor } from "./Autor";
import { type Editora } from "./Editora";

export class Livro {
  id!: number;
  dataCadastro!: string;
  ativo!: boolean;
  nome!: string;
  dataLancamento?: string | null;
  genero!: string;
  autor?: Autor;
  editora?: Editora;
}