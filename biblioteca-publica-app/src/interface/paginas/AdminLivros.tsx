import React, { useEffect, useState } from "react";
import { RepositorioLivro } from "../../repositorios/RepositorioLivro";

export default function AdminLivros() {
  const [livros, setLivros] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);

  async function load() {
    setLoading(true);
    try {
      const list = await RepositorioLivro.listar();
      setLivros(list);
    } catch (e) {
      console.error(e);
      alert("Erro carregando livros");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { load(); }, []);

  async function excluir(id: number) {
    if (!confirm("Excluir livro?")) return;
    try {
      await RepositorioLivro.excluir(id);
      await load();
    } catch (e) {
      console.error(e);
      alert("Erro ao excluir");
    }
  }

  return (
    <div className="bg-white rounded shadow p-4">
      <div className="flex items-center justify-between mb-4">
        <h3 className="text-lg font-semibold">Livros</h3>
        <div className="flex gap-2">
          <button className="px-3 py-1 rounded bg-green-600 text-white" onClick={() => window.location.href = "/admin/livros/novo"}>Novo</button>
          <button className="px-3 py-1 rounded border" onClick={load}>Recarregar</button>
        </div>
      </div>

      {loading && <div>Carregando...</div>}
      {!loading && livros.length === 0 && <div className="text-sm text-gray-500">Nenhum livro encontrado</div>}

      <div className="space-y-3">
        {livros.map(l => (
          <div key={l.id} className="p-3 border rounded flex items-center justify-between">
            <div>
              <div className="font-semibold">{l.nome}</div>
              <div className="text-sm text-gray-500">{l.autor?.nome ?? "—"} • {l.editora?.nome ?? "—"}</div>
            </div>
            <div className="flex gap-2">
              <button className="px-2 py-1 rounded bg-blue-600 text-white" onClick={() => window.location.href = `/admin/livros/editar/${l.id}`}>Editar</button>
              <button className="px-2 py-1 rounded bg-red-500 text-white" onClick={() => excluir(l.id)}>Excluir</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
