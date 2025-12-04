import api from "./api";
import { type Usuario } from "../dominio/Usuario";

export const RepositorioUsuario = {
  listar: async (): Promise<Usuario[]> => {
    const r = await api.get<Usuario[]>("/usuario/todos");
    return r.data;
  },

  buscar: async (id: number): Promise<Usuario> => {
    const r = await api.get<Usuario>(`/usuario/${id}`);
    return r.data;
  },

  salvar: async (usuario: Partial<Usuario>): Promise<Usuario> => {
    const r = await api.post<Usuario>("/usuario/salvar", usuario);
    return r.data;
  },

  excluir: async (id: number): Promise<void> => {
    await api.delete(`/usuario/excluir/${id}`);
  },

  login: async (login: string, senha: string): Promise<boolean> => {
    const r = await api.post<void>("/usuario/login", { login, senha });
    return r.status === 200;
  }
};