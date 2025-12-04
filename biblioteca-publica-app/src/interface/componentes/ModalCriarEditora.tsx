import React, { useState } from 'react';
import { useEditora } from '../../hooks/useEditora';

interface Props {
  onFechar: () => void;
  onSucesso: () => void;
}

export default function ModalCriarEditora({ onFechar, onSucesso }: Props) {
  const { criarEditora } = useEditora();
  const [nome, setNome] = useState('');
  const [loading, setLoading] = useState(false);
  const [erro, setErro] = useState('');

  const criar = async () => {
    if (!nome.trim()) return setErro('O nome é obrigatório');
    setErro('');
    setLoading(true);
    try {
      await criarEditora({ nome });
      setNome('');
      onSucesso();
    } catch (e: any) {
      setErro(e.message || 'Erro ao criar editora');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-white p-6 rounded-xl w-96 shadow-lg">
        <h2 className="text-xl font-bold mb-4">Criar Editora</h2>
        {erro && <div className="text-red-600 mb-2">{erro}</div>}
        <input
          type="text"
          value={nome}
          onChange={e => setNome(e.target.value)}
          className="w-full border rounded px-3 py-2 mb-4"
          placeholder="Nome da editora"
        />
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
