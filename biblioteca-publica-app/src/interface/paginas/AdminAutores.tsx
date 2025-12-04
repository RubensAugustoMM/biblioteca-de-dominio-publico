import React, { useEffect, useState } from "react";
import { RepositorioAutor } from "../../repositorios/RepositorioAutor";

export default function AdminAutores() {
  const [autores, setAutores] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);
  const [nome, setNome] = useState("");
  const [editando, setEditando] = useState<any | null>(null);

  async function load() {
    setLoading(true);
    try {
      const list = await RepositorioAutor.listar();
      setAutores(list);
    } catch (e) {
      console.error(e);
      alert("Erro carregando autores");
    } finally { setLoading(false); }
  }

  useEffect(() => { load(); }, []);

  async function salvar() {
    try {
      const payload = editando ? { id: editando.id, nome } : { nome };
      await RepositorioAutor.salvar(payload);
      setNome("");
      setEditando(null);
      await load();
    } catch (e) {
      console.error(e);
      alert("Erro ao salvar autor");
    }
  }

  async function excluir(id: number) {
    if (!confirm("Excluir autor?")) return;
    try {
      await RepositorioAutor.deletar(id);
      await load();
    } catch (e) {
      console.error(e);
      alert("Erro ao excluir");
    }
  }

  function startEdit(a: any) {
    setEditando(a);
    setNome(a.nome);
  }

  return (
    <div className="bg-white rounded shadow p-4">
      <h4 className="font-semibold mb-3">Autores</h4>

      <div className="flex gap-2 mb-3">
        <input className="flex-1 p-2 border rounded" placeholder="Nome do autor" value={nome} onChange={e => setNome(e.target.value)} />
        <button className="px-3 py-1 rounded bg-emerald-600 text-white" onClick={salvar}>{editando ? "Salvar" : "Adicionar"}</button>
      </div>

      <div className="space-y-2 max-h-48 overflow-auto">
        {loading && <div>Carregando...</div>}
        {autores.map(a => (
          <div key={a.id} className="flex items-center justify-between p-2 border rounded">
            <div>{a.nome}</div>
            <div className="flex gap-2">
              <button className="px-2 py-1 bg-blue-600 text-white rounded" onClick={() => startEdit(a)}>Editar</button>
              <button className="px-2 py-1 bg-red-500 text-white rounded" onClick={() => excluir(a.id)}>Excluir</button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
