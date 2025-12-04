import api from "./api";
import { type Autor } from "../dominio/Autor";

export const RepositorioAutor = {
  listar: async (): Promise<Autor[]> => {
    const r = await api.get<Autor[]>("/autor/todos");
    return r.data;
  },

  buscar: async (id: number): Promise<Autor> => {
    const r = await api.get<Autor>(`/autor/${id}`);
    return r.data;
  },

  salvar: async (autor: Partial<Autor>): Promise<Autor> => {
    const r = await api.post<Autor>("/autor/salvar", autor);
    return r.data;
  },

  deletar: async (id: number): Promise<void> => {
    await api.delete(`/autor/${id}`);
  }
};