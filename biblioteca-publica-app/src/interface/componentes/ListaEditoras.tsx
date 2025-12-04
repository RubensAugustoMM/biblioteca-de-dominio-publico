import React from 'react';
import { useEditora } from '../../hooks/useEditora';

export default function ListaEditoras() {
  const { editoras, deletar, abrirModalEditora, carregar, loading, error } = useEditora();

  return (
    <div>
      {error && <div className="mb-4 bg-red-50 border border-red-200 text-red-700 px-4 py-2 rounded">{error}</div>}

      <div className="mb-4 flex justify-end">
        <button
          className="px-3 py-1 rounded bg-blue-500 text-white text-sm"
          onClick={() => abrirModalEditora(null)}
        >
          Criar Editora
        </button>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
        {loading ? (
          <div>Carregando...</div>
        ) : (
          editoras.map((e) => (
            <div key={e.id} className="bg-white p-4 rounded shadow-sm">
              <h3 className="font-semibold text-lg">{e.nome}</h3>

              <div className="mt-3 flex gap-2">
                <button
                  className="px-2 py-1 rounded bg-green-500 text-white text-sm"
                  onClick={() => abrirModalEditora(e)}
                >
                  Editar
                </button>
                <button
                  className="px-2 py-1 rounded bg-red-500 text-white text-sm"
                  onClick={() => deletarEditora(e.id)}
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
