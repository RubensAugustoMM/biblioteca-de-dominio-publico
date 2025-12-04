import { type Livro } from "./Livro";
import { type Usuario } from "./Usuario";

export class LivroUsuario {
  id!: number;
  dataCadastro?: string;
  ativo?: boolean;
  livro!: Livro | { id: number };
  usuario!: Usuario | { id: number };
  tipo!: number;
}