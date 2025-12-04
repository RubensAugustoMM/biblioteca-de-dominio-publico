import api from "./api";
import { type LivroUsuario } from "../dominio/LivroUsuario";

export const RepositorioLivroUsuario = {
  listar: async (): Promise<LivroUsuario[]> => {
    const r = await api.get<LivroUsuario[]>("/livro/usuarios");
    return r.data;
  },

  buscar: async (id: number): Promise<LivroUsuario> => {
    const r = await api.get<LivroUsuario>(`/livro/usuario/${id}`);
    return r.data;
  },

  salvarFavorito: async (idLivro: number, idUsuario: number): Promise<void> => {
    await api.post(`/livro/favoritar/${idLivro}/${idUsuario}`);
  },

  salvar: async (payload: Partial<LivroUsuario>): Promise<LivroUsuario> => {
    const r = await api.post<LivroUsuario>("/livro/usuario", payload);
    return r.data;
  },

  deletar: async (id: number): Promise<void> => {
    await api.delete(`/livro/usuario/${id}`);
  },
};