import { useState } from 'react';
import { Usuario } from '../dominio/Usuario';
import { RepositorioUsuario } from '../repositorios/RepositorioUsuario';

export function useUsuario() {
  const [usuario, setUsuario] = useState<Usuario | null>(() => {
    try {
      const raw = localStorage.getItem('usuario_atual');
      return raw ? JSON.parse(raw) : null;
    } catch { return null; }
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  async function login(loginStr: string, senha: string) {
    setLoading(true); setError(null);
    try {
      const ok = await RepositorioUsuario.login(loginStr, senha);
      if (!ok) {
        setError('Credenciais inválidas');
        return false;
      }
      // opcional: buscar dados completos do usuário e armazenar
      const usuarios = await RepositorioUsuario.listar();
      const me = usuarios.find(u => u.login === loginStr) ?? null;
      setUsuario(me);
      if (me) localStorage.setItem('usuario_atual', JSON.stringify(me));
      return true;
    } catch (e: any) {
      console.error('[useUsuario.login] erro:', e);
      setError(e?.message ?? 'Erro inesperado');
      return false;
    } finally {
      setLoading(false);
    }
  }

  async function criar(payload: Partial<Usuario>) {
    setLoading(true); setError(null);
    try {
      const created = await RepositorioUsuario.salvar(payload);
      setUsuario(created);
      localStorage.setItem('usuario_atual', JSON.stringify(created));
      return created;
    } catch (e: any) {
      setError(e?.message ?? 'Erro ao criar usuário');
      throw e;
    } finally { setLoading(false); }
  }

  function logout() {
    setUsuario(null);
    localStorage.removeItem('usuario_atual');
  }

  return { usuario, loading, error, login, criar, logout };
}
