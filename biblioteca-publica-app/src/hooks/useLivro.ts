import { useEffect, useState } from 'react';
import { Livro } from '../dominio/Livro';
import { RepositorioLivro } from '../repositorios/RepositorioLivro';

export function useLivro() {
  const [livros, setLivros] = useState<Livro[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [modalAberto, setModalAberto] = useState(false);
  const [livroSelecionado, setLivroSelecionado] = useState<Livro | null>(null);

  useEffect(() => { load(); }, []);

  async function load() {
    setLoading(true); setError(null);
    try {
      const data = await RepositorioLivro.listar();
      setLivros(data);
    } catch (e: any) {
      setError(e?.message ?? 'Erro ao carregar livros');
    } finally { setLoading(false); }
  }

  async function deletar(id: number) {
    try {
      await RepositorioLivro.excluir(id);
      setLivros(livros.filter(l => l.id !== id));
    } catch (e: any) {
      setError(e?.message ?? 'Erro ao deletar livro');
    }
  }

  async function salvar(livro: Livro) {
    try {
      const salvo = await RepositorioLivro.salvar(livro);
      const idx = livros.findIndex(l => l.id === salvo.id);
      if (idx >= 0) {
        const copia = [...livros];
        copia[idx] = salvo;
        setLivros(copia);
      } else {
        setLivros([salvo, ...livros]);
      }
    } catch (e: any) {
      setError(e?.message ?? 'Erro ao salvar livro');
    }
  }

  function abrirModal(livro?: Livro) {
    setLivroSelecionado(livro ?? null);
    setModalAberto(true);
  }

  function fecharModal() {
    setLivroSelecionado(null);
    setModalAberto(false);
  }

  return {
    livros,
    loading,
    error,
    modalAberto,
    livroSelecionado,
    abrirModal,
    fecharModal,
    salvar,
    deletar,
    reload: load
  };
}
