import api from "./api";
import { type Livro } from "../dominio/Livro";

export const RepositorioLivro = {
  listar: async (): Promise<Livro[]> => {
    const r = await api.get<Livro[]>("/livro/todos");
    return r.data;
  },

  buscar: async (id: number): Promise<Livro> => {
    const r = await api.get<Livro>(`/livro/${id}`);
    return r.data;
  },

  salvar: async (livro: Partial<Livro>): Promise<Livro> => {
    const r = await api.post<Livro>("/livro/salvar", livro);
    return r.data;
  },

  excluir: async (id: number): Promise<void> => {
    await api.delete(`/livro/${id}`);
  },

  favoritar: async (idLivro: number, idUsuario: number): Promise<void> => {
    await api.post(`/livro/favoritar/${idLivro}/${idUsuario}`);
  }
};