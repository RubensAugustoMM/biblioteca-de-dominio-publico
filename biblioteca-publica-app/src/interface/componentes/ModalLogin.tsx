import React, { useState } from 'react';
import { useUsuario } from '../../hooks/useUsuario';

export default function ModalLogin({ onFechar, onSucesso }: { onFechar: () => void; onSucesso?: () => void }) {
    const { login } = useUsuario();
    const [form, setForm] = useState({ login: '', senha: '' });
    const [carregando, setCarregando] = useState(false);
    const [erro, setErro] = useState<string | null>(null);

    async function doLogin() {
        setCarregando(true);
        setErro(null);
        try {
            console.log('[ModalLogin] tentando login', form);
            const ok = await login(form.login, form.senha);
            console.log('[ModalLogin] resultado login:', ok);
            if (ok) {
                if (onSucesso) onSucesso();
                onFechar();
            } else {
                setErro('Login falhou: usuário/senha inválidos');
            }
        } catch (e: any) {
            console.error('[ModalLogin] exceção no login:', e);
            setErro(e?.message ?? 'Erro ao conectar-se ao servidor');
        } finally {
            setCarregando(false);
        }
    }

    return (
        <div className="fixed inset-0 z-40 flex items-center justify-center bg-black/40 p-4">
            <div className="w-full max-w-md rounded-2xl bg-white p-6 shadow-xl">
                <h3 className="text-xl font-bold mb-2">Entrar</h3>
                <p className="text-sm text-slate-500 mb-4">Use seu login para acessar recursos</p>

                {erro && <div className="mb-3 text-sm text-red-600">{erro}</div>}

                <input className="w-full mb-3 px-3 py-2 border rounded-md" placeholder="Login" value={form.login} onChange={e => setForm(s => ({ ...s, login: e.target.value }))} />
                <input type="password" className="w-full mb-4 px-3 py-2 border rounded-md" placeholder="Senha" value={form.senha} onChange={e => setForm(s => ({ ...s, senha: e.target.value }))} />

                <div className="flex justify-end gap-2">
                    <button className="px-3 py-2 rounded-md bg-white border" onClick={onFechar}>Cancelar</button>
                    <button className="px-4 py-2 rounded-md bg-emerald-600 text-white" onClick={doLogin} disabled={carregando}>{carregando ? 'Entrando...' : 'Entrar'}</button>
                </div>
            </div>
        </div>
    );
}
