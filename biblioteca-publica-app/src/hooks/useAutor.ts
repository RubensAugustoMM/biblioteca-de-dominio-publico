import { useState, useEffect, useCallback } from 'react';
import { RepositorioAutor } from '../repositorios/RepositorioAutor';

export interface Autor {
  id: number;
  nome: string;
  dataCadastro: string;
}

export function useAutor() {
  const [autores, setAutores] = useState<Autor[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [modalAberto, setModalAberto] = useState(false);
  const [autorSelecionado, setAutorSelecionado] = useState<Autor | null>(null);

  const carregar = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await RepositorioAutor.listar();
      setAutores(data);
    } catch (err: any) {
      setError(err.message || 'Erro ao carregar autores');
    } finally {
      setLoading(false);
    }
  }, []);

  const deletar = useCallback(async (id: number) => {
    try {
      await RepositorioAutor.deletar(id);
      setAutores(autores.filter(a => a.id !== id));
    } catch (err: any) {
      setError(err.message || 'Erro ao deletar autor');
    }
  }, [autores]);

  const salvar = useCallback(async (autor: Partial<Autor>) => {
    try {
      const salvo = await RepositorioAutor.salvar(autor);
      const idx = autores.findIndex(a => a.id === salvo.id);
      if (idx >= 0) {
        const copia = [...autores];
        copia[idx] = salvo;
        setAutores(copia);
      } else {
        setAutores([salvo, ...autores]);
      }
    } catch (err: any) {
      setError(err.message || 'Erro ao salvar autor');
      throw err;
    }
  }, [autores]);

  function abrirModal(autor?: Autor) {
    setAutorSelecionado(autor ?? null);
    setModalAberto(true);
  }

  function fecharModal() {
    setAutorSelecionado(null);
    setModalAberto(false);
  }

  useEffect(() => { carregar(); }, [carregar]);

  return {
    autores,
    loading,
    error,
    modalAberto,
    autorSelecionado,
    abrirModal,
    fecharModal,
    salvar,
    deletar,
    carregar
  };
}
