import React from 'react';
import { useAutor } from '../../hooks/useAutor';

export default function ListaAutores() {
  const { autores, deletar, abrirModalAutor, carregar, loading, error } = useAutor();

  return (
    <div>
      {error && <div className="mb-4 bg-red-50 border border-red-200 text-red-700 px-4 py-2 rounded">{error}</div>}

      <div className="mb-4 flex justify-end">
        <button
          className="px-3 py-1 rounded bg-blue-500 text-white text-sm"
          onClick={() => abrirModalAutor(null)}
        >
          Criar Autor
        </button>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
        {loading ? (
          <div>Carregando...</div>
        ) : (
          autores.map((a) => (
            <div key={a.id} className="bg-white p-4 rounded shadow-sm">
              <h3 className="font-semibold text-lg">{a.nome}</h3>

              <div className="mt-3 flex gap-2">
                <button
                  className="px-2 py-1 rounded bg-green-500 text-white text-sm"
                  onClick={() => abrirModalAutor(a)}
                >
                  Editar
                </button>
                <button
                  className="px-2 py-1 rounded bg-red-500 text-white text-sm"
                  onClick={() => deletarAutor(a.id)}
                >
                  Excluir
                </button>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
}
