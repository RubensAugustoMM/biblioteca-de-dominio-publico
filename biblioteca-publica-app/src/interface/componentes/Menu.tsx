import React from "react";
import { Link } from "react-router-dom";
import { useUsuario } from "../../hooks/useUsuario";

export default function Menu() {
  const { usuario, logout } = useUsuario();

  return (
    <header className="flex items-center justify-between bg-white p-4 rounded shadow">
      <div className="flex items-center gap-4">
        <div className="w-10 h-10 rounded bg-emerald-600 text-white flex items-center justify-center font-bold">BP</div>
        <div>
          <div className="font-semibold text-lg">Biblioteca Pública</div>
          <div className="text-sm text-gray-500">Catálogo</div>
        </div>
      </div>

      <nav className="flex items-center gap-4">
        <Link to="/" className="text-sm text-gray-700">Home</Link>
        {usuario && <Link to="/favoritos" className="text-sm text-gray-700">Favoritos</Link>}
        {usuario && <Link to="/admin" className="text-sm text-gray-700">Admin</Link>}
        {!usuario && <Link to="/login" className="px-3 py-1 rounded border">Login</Link>}
        {usuario && <button onClick={logout} className="px-3 py-1 rounded bg-red-500 text-white">Sair</button>}
      </nav>
    </header>
  );
}
