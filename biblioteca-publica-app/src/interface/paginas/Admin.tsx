import React from "react";
import Menu from "../componentes/Menu";
import AdminLivros from "./AdminLivros";
import AdminAutores from "./AdminAutores";
import AdminEditoras from "./AdminEditoras";

export default function Admin() {
  return (
    <div className="min-h-screen bg-gray-50">
      <div className="max-w-7xl mx-auto p-6">
        <Menu />
        <h1 className="text-2xl font-bold mt-6">Painel de Administração</h1>
        <p className="text-sm text-gray-500 mb-6">Gerencie livros, autores e editoras</p>

        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
          <div className="lg:col-span-2">
            <AdminLivros />
          </div>

          <div className="space-y-6">
            <AdminAutores />
            <AdminEditoras />
          </div>
        </div>
      </div>
    </div>
  );
}
