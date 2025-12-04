import api from "./api";
import { type Editora } from "../dominio/Editora";

export const RepositorioEditora = {
  listar: async (): Promise<Editora[]> => {
    const r = await api.get<Editora[]>("/editora");
    return r.data;
  },

  buscar: async (id: number): Promise<Editora> => {
    const r = await api.get<Editora>(`/editora/${id}`);
    return r.data;
  },

  salvar: async (editora: Partial<Editora>): Promise<Editora> => {
    const r = await api.post<Editora>("/editora/salvar", editora);
    return r.data;
  },

  deletar: async (id: number): Promise<void> => {
    await api.delete(`/editora/${id}`);
  }
};