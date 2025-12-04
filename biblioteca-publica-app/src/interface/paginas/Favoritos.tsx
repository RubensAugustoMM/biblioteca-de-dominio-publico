import React, { useEffect, useState } from 'react';
import { useUsuario } from '../../hooks/useUsuario';
import { Livro } from '../../dominio/Livro';
import axios from 'axios';

export default function Favoritos() {
  const { usuario } = useUsuario();
  const [livros, setLivros] = useState<Livro[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!usuario) {
      setLoading(false);
      return;
    }

    axios.get(`/api/usuario/${usuario.id}/favoritos`)
      .then(res => setLivros(res.data))
      .finally(() => setLoading(false));
  }, [usuario]);

  if (loading) return <div>Carregando favoritos...</div>;
  if (!usuario) return <div>Faça login para ver seus favoritos.</div>;

  return (
    <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
      {livros.map(l => (
        <div key={l.id} className="bg-white p-4 rounded shadow">
          <h3 className="font-semibold">{l.nome}</h3>
          <div className="text-sm text-gray-500">{l.autor?.nome ?? '-'} • {l.editora?.nome ?? '-'}</div>
        </div>
      ))}
    </div>
  );
}