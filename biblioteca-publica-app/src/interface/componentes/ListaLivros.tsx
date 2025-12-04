import React from 'react';
import { Livro } from '../../dominio/Livro';
import { useLivro } from '../../hooks/useLivro';

type Props = {
  livros?: Livro[]; 
  onExigirLogin?: () => void; 
};

export default function ListaLivros({ livros, onExigirLogin }: Props) {
  const { livros: livrosDoHook, deletarLivro, abrirModalLivro } = useLivro();
  const lista = livros ?? livrosDoHook; 

  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
      {lista.map((l) => (
        <div key={l.id} className="bg-white p-4 rounded shadow-sm">
          <h3 className="font-semibold text-lg">{l.nome}</h3>
          <div className="text-sm text-gray-600">{l.genero}</div>
          <div className="mt-2 text-sm text-gray-500">
            {l.autor?.nome ?? '-'} • {l.editora?.nome ?? '-'}
          </div>

          <div className="mt-3 flex gap-2">
            <button
              className="px-2 py-1 rounded bg-green-500 text-white text-sm"
              onClick={() => abrirModalLivro(l)}
            >
              Editar
            </button>
            <button
              className="px-2 py-1 rounded bg-red-500 text-white text-sm"
              onClick={() => deletarLivro(l.id)}
            >
              Excluir
            </button>
            <button
              className="px-2 py-1 rounded bg-yellow-500 text-white text-sm"
              onClick={() => {
                if (onExigirLogin) onExigirLogin();
              }}
            >
              Favoritar
            </button>
          </div>
        </div>
      ))}
    </div>
  );
}
