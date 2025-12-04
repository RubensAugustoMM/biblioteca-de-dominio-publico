import React from 'react';
import { createRoot } from 'react-dom/client';
import LayoutPrincipal from './interface/LayoutPrincipal';
import { UsuarioProvider } from './contexts/UsuarioContext';
import './styles/tailwind.css';

const root = createRoot(document.getElementById('root')!);
root.render(
  <React.StrictMode>
    <UsuarioProvider>
      <LayoutPrincipal />
    </UsuarioProvider>
  </React.StrictMode>
);