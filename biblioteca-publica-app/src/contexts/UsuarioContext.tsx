import React, { createContext, useContext, useEffect, useState } from 'react';
import { RepositorioUsuario } from '../repositorios/RepositorioUsuario';
import { Usuario } from '../dominio/Usuario';

type UsuarioContextValue = {
  usuario: Usuario | null;
  loading: boolean;
  error: string | null;
  login: (login: string, senha: string) => Promise<boolean>;
  criar: (payload: Partial<Usuario>) => Promise<Usuario>;
  logout: () => void;
};

const UsuarioContext = createContext<UsuarioContextValue | undefined>(undefined);

export function UsuarioProvider({ children }: { children: React.ReactNode }) {
  const [usuario, setUsuario] = useState<Usuario | null>(() => {
    try {
      const raw = localStorage.getItem('usuario_atual');
      return raw ? JSON.parse(raw) as Usuario : null;
    } catch { return null; }
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    // opcional: sincronizar com storage (ouvíveis de outras tabs)
    const handler = (ev: StorageEvent) => {
      if (ev.key === 'usuario_atual') {
        try {
          setUsuario(ev.newValue ? JSON.parse(ev.newValue) : null);
        } catch { setUsuario(null); }
      }
    };
    window.addEventListener('storage', handler);
    return () => window.removeEventListener('storage', handler);
  }, []);

  async function login(loginStr: string, senha: string) {
    setLoading(true);
    setError(null);
    try {
      const ok = await RepositorioUsuario.login(loginStr, senha);
      if (!ok) {
        setError('Credenciais inválidas');
        return false;
      }
      // opcional: buscar usuário completo
      const lista = await RepositorioUsuario.listar();
      const me = lista.find(u => u.login === loginStr) ?? null;
      setUsuario(me);
      if (me) localStorage.setItem('usuario_atual', JSON.stringify(me));
      return true;
    } catch (e: any) {
      setError(e?.message ?? 'Erro no login');
      console.error('UsuarioContext.login error', e);
      return false;
    } finally {
      setLoading(false);
    }
  }

  async function criar(payload: Partial<Usuario>) {
    setLoading(true);
    setError(null);
    try {
      const created = await RepositorioUsuario.salvar(payload);
      setUsuario(created);
      localStorage.setItem('usuario_atual', JSON.stringify(created));
      return created;
    } finally {
      setLoading(false);
    }
  }

  function logout() {
    setUsuario(null);
    localStorage.removeItem('usuario_atual');
  }

  return (
    <UsuarioContext.Provider value={{ usuario, loading, error, login, criar, logout }}>
      {children}
    </UsuarioContext.Provider>
  );
}

export function useUsuarioContext() {
  const ctx = useContext(UsuarioContext);
  if (!ctx) throw new Error('useUsuarioContext must be used within UsuarioProvider');
  return ctx;
}
