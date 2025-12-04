import React, { useState } from 'react';
import { useUsuario } from '../../hooks/useUsuario';

export default function ModalCriarConta({ onFechar, onSucesso }: { onFechar: () => void; onSucesso?: () => void }) {
  const { criar } = useUsuario();
  const [form, setForm] = useState({ login: '', nome: '', senha: '', email: '' });
  const [carregando, setCarregando] = useState(false);
  const [erro, setErro] = useState<string | null>(null);

  async function doCriar() {
    setCarregando(true); setErro(null);
    try {
      await criar(form as any);
      if (onSucesso) onSucesso();
      onFechar();
    } catch (e: any) {
      setErro(e?.message ?? 'Erro ao criar conta');
    } finally { setCarregando(false); }
  }

  return (
    <div className="fixed inset-0 z-40 flex items-center justify-center bg-black/40 p-4">
      <div className="w-full max-w-md rounded-2xl bg-white p-6 shadow-xl">
        <h3 className="text-xl font-bold mb-2">Criar conta</h3>
        <p className="text-sm text-slate-500 mb-4">Cadastre-se para favoritar e gerenciar empréstimos</p>

        {erro && <div className="mb-3 text-sm text-red-600">{erro}</div>}

        <input className="w-full mb-2 px-3 py-2 border rounded-md" placeholder="Login" value={form.login} onChange={e => setForm(s => ({ ...s, login: e.target.value }))} />
        <input className="w-full mb-2 px-3 py-2 border rounded-md" placeholder="Nome" value={form.nome} onChange={e => setForm(s => ({ ...s, nome: e.target.value }))} />
        <input className="w-full mb-2 px-3 py-2 border rounded-md" placeholder="Email" value={form.email} onChange={e => setForm(s => ({ ...s, email: e.target.value }))} />
        <input type="password" className="w-full mb-4 px-3 py-2 border rounded-md" placeholder="Senha" value={form.senha} onChange={e => setForm(s => ({ ...s, senha: e.target.value }))} />

        <div className="flex justify-end gap-2">
          <button className="px-3 py-2 rounded-md bg-white border" onClick={onFechar}>Cancelar</button>
          <button className="px-4 py-2 rounded-md bg-emerald-600 text-white" onClick={doCriar} disabled={carregando}>{carregando ? 'Criando...' : 'Criar'}</button>
        </div>
      </div>
    </div>
  );
}
