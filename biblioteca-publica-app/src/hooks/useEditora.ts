import { useState, useEffect, useCallback } from 'react';
import { RepositorioEditora } from '../repositorios/RepositorioEditora';

export interface Editora {
  id: number;
  nome: string;
  dataCadastro: string;
}

export function useEditora() {
  const [editoras, setEditoras] = useState<Editora[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [modalAberto, setModalAberto] = useState(false);
  const [editoraSelecionada, setEditoraSelecionada] = useState<Editora | null>(null);

  const carregar = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await RepositorioEditora.listar();
      setEditoras(data);
    } catch (err: any) {
      setError(err.message || 'Erro ao carregar editoras');
    } finally {
      setLoading(false);
    }
  }, []);

  const deletar = useCallback(async (id: number) => {
    try {
      await RepositorioEditora.deletar(id);
      setEditoras(editoras.filter(e => e.id !== id));
    } catch (err: any) {
      setError(err.message || 'Erro ao deletar editora');
    }
  }, [editoras]);

  const salvar = useCallback(async (editora: Partial<Editora>) => {
    try {
      const salvo = await RepositorioEditora.salvar(editora);
      const idx = editoras.findIndex(e => e.id === salvo.id);
      if (idx >= 0) {
        const copia = [...editoras];
        copia[idx] = salvo;
        setEditoras(copia);
      } else {
        setEditoras([salvo, ...editoras]);
      }
    } catch (err: any) {
      setError(err.message || 'Erro ao salvar editora');
      throw err;
    }
  }, [editoras]);

  function abrirModal(editora?: Editora) {
    setEditoraSelecionada(editora ?? null);
    setModalAberto(true);
  }

  function fecharModal() {
    setEditoraSelecionada(null);
    setModalAberto(false);
  }

  useEffect(() => { carregar(); }, [carregar]);

  return {
    editoras,
    loading,
    error,
    modalAberto,
    editoraSelecionada,
    abrirModal,
    fecharModal,
    salvar,
    deletar,
    carregar
  };
}
