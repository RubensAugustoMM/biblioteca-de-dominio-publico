import React, { useState } from 'react';
import { useLivro } from '../../hooks/useLivro';
import { useAutor } from '../../hooks/useAutor';
import { useEditora } from '../../hooks/useEditora';

interface Props {
  onFechar: () => void;
  onSucesso: () => void;
}

export default function ModalCriarLivro({ onFechar, onSucesso }: Props) {
  const { criarLivro } = useLivro();
  const { autores } = useAutor();
  const { editoras } = useEditora();

  const [nome, setNome] = useState('');
  const [genero, setGenero] = useState('');
  const [autorId, setAutorId] = useState<number | null>(null);
  const [editoraId, setEditoraId] = useState<number | null>(null);
  const [loading, setLoading] = useState(false);
  const [erro, setErro] = useState('');

  const criar = async () => {
    if (!nome.trim() || !genero.trim() || !autorId || !editoraId)
      return setErro('Todos os campos são obrigatórios');
    setErro('');
    setLoading(true);
    try {
      await criarLivro({ nome, genero, autorId, editoraId });
      setNome('');
      setGenero('');
      setAutorId(null);
      setEditoraId(null);
      onSucesso();
    } catch (e: any) {
      setErro(e.message || 'Erro ao criar livro');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-white p-6 rounded-xl w-96 shadow-lg">
        <h2 className="text-xl font-bold mb-4">Criar Livro</h2>
        {erro && <div className="text-red-600 mb-2">{erro}</div>}

        <input
          type="text"
          value={nome}
          onChange={e => setNome(e.target.value)}
          className="w-full border rounded px-3 py-2 mb-2"
          placeholder="Nome do livro"
        />
        <input
          type="text"
          value={genero}
          onChange={e => setGenero(e.target.value)}
          className="w-full border rounded px-3 py-2 mb-2"
          placeholder="Gênero"
        />

        <select
          value={autorId ?? ''}
          onChange={e => setAutorId(Number(e.target.value))}
          className="w-full border rounded px-3 py-2 mb-2"
        >
          <option value="">Selecione um autor</option>
          {autores.map(a => (
            <option key={a.id} value={a.id}>{a.nome}</option>
          ))}
        </select>

        <select
          value={editoraId ?? ''}
          onChange={e => setEditoraId(Number(e.target.value))}
          className="w-full border rounded px-3 py-2 mb-4"
        >
          <option value="">Selecione uma editora</option>
          {editoras.map(e => (
            <option key={e.id} value={e.id}>{e.nome}</option>
          ))}
        </select>

        <div className="flex justify-end gap-2">
          <button
            className="px-4 py-2 rounded bg-gray-200 hover:bg-gray-300"
            onClick={onFechar}
          >
            Cancelar
          </button>
          <button
            className="px-4 py-2 rounded bg-emerald-600 text-white hover:bg-emerald-700"
            onClick={criar}
            disabled={loading}
          >
            {loading ? 'Criando...' : 'Criar'}
          </button>
        </div>
      </div>
    </div>
  );
}
