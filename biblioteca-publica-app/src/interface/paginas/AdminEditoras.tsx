import React, { useEffect, useState } from "react";
import { RepositorioEditora } from "../../repositorios/RepositorioEditora";

export default function AdminEditoras() {
  const [editoras, setEditoras] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);
  const [nome, setNome] = useState("");
  const [editando, setEditando] = useState<any | null>(null);

  async function load() {
    setLoading(true);
    try {
      const list = await RepositorioEditora.listar();
      setEditoras(list);
    } catch (e) {
      console.error(e);
      alert("Erro carregando editoras");
    } finally { setLoading(false); }
  }

  useEffect(() => { load(); }, []);

  async function salvar() {
    try {
      const payload = editando ? { id: editando.id, nome } : { nome };
      await RepositorioEditora.salvar(payload);
      setNome("");
      setEditando(null);
      await load();
    } catch (e) {
      console.error(e);
      alert("Erro ao salvar editora");
    }
  }

  async function excluir(id: number) {
    if (!confirm("Excluir editora?")) return;
    try {
      await RepositorioEditora.deletar(id);
      await load();
    } catch (e) {
      console.error(e);
      alert("Erro ao excluir");
    }
  }

  function startEdit(ed: any) {
    setEditando(ed);
    setNome(ed.nome);
  }

  return (
    <div className="bg-white rounded shadow p-4">
      <h4 className="font-semibold mb-3">Editoras</h4>

      <div className="flex gap-2 mb-3">
        <input className="flex-1 p-2 border rounded" placeholder="Nome da editora" value={nome} onChange={e => setNome(e.target.value)} />
        <button className="px-3 py-1 rounded bg-emerald-600 text-white" onClick={salvar}>{editando ? "Salvar" : "Adicionar"}</button>
      </div>

      <div className="space-y-2 max-h-48 overflow-auto">
        {loading && <div>Carregando...</div>}
        {editoras.map(ed => (
          <div key={ed.id} className="flex items-center justify-between p-2 border rounded">
            <div>{ed.nome}</div>
            <div className="flex gap-2">
              <button className="px-2 py-1 bg-blue-600 text-white rounded" onClick={() => startEdit(ed)}>Editar</button>
              <button className="px-2 py-1 bg-red-500 text-white rounded" onClick={() => excluir(ed.id)}>Excluir</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
