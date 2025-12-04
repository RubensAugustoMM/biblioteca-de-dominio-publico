import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { UsuarioProvider } from './contexts/UsuarioContext';
import { useUsuario } from './hooks/useUsuario';
import LayoutPrincipal from './interface/LayoutPrincipal';
import Favoritos from '.\interface\paginas\Favoritos';
import Admin from './interface/paginas/Admin';
import AdminLivros from './interface/paginas/AdminLivros';
import AdminAutores from './interface/paginas/AdminAutores';
import AdminEditoras from './interface/paginas/AdminEditoras';

function RotaPrivada({ children }: { children: React.ReactNode }) {
  const { usuario, loading } = useUsuario();
  if (loading) return <div className="p-6">Carregando...</div>;
  if (!usuario) return <Navigate to="/" replace />;
  return <>{children}</>;
}

export default function App() {
  return (
    <UsuarioProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LayoutPrincipal />}>
            <Route path="favoritos" element={<RotaPrivada><Favoritos /></RotaPrivada>} />
            <Route path="admin" element={<RotaPrivada><Admin /></RotaPrivada>} />
            <Route path="admin/livros" element={<RotaPrivada><AdminLivros /></RotaPrivada>} />
            <Route path="admin/autores" element={<RotaPrivada><AdminAutores /></RotaPrivada>} />
            <Route path="admin/editoras" element={<RotaPrivada><AdminEditoras /></RotaPrivada>} />
            <Route path="*" element={<Navigate to="/" replace />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </UsuarioProvider>
  );
}