export class Usuario {
  id!: number;
  dataCadastro!: string;
  ativo!: boolean;
  login!: string;
  nome!: string;
  senha?: string;
  senhaSalt?: string;
  cpf?: string | null;
  email?: string | null;
  idade?: number;
}