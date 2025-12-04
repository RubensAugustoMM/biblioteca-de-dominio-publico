import React, { useState } from 'react';
import ListaLivros from './componentes/ListaLivros';
import TelaFavoritos from './paginas/Favoritos';
import ListaAutores from './componentes/ListaAutores';
import ListaEditoras from './componentes/ListaEditoras';
import ModalLogin from './componentes/ModalLogin';
import ModalCriarConta from './componentes/ModalCriarConta';
import { useUsuario } from '../hooks/useUsuario';

type Aba = 'livros' | 'favoritos' | 'autores' | 'editoras';

export default function LayoutPrincipal() {
    const { usuario, logout } = useUsuario();
    const [mostrarLogin, setMostrarLogin] = useState(false);
    const [mostrarCriar, setMostrarCriar] = useState(false);
    const [abaAtiva, setAbaAtiva] = useState<Aba>('livros');

    const renderConteudo = () => {
        switch (abaAtiva) {
            case 'livros':
                return <ListaLivros onExigirLogin={() => setMostrarLogin(true)} />;
            case 'favoritos':
                return <TelaFavoritos />;
            case 'autores':
                return <ListaAutores />;
            case 'editoras':
                return <ListaEditoras />;
        }
    };

    return (
        <div className="min-h-screen bg-gradient-to-b from-slate-50 to-white py-8">
            <header className="mx-auto max-w-6xl px-6">
                <div className="bg-white/60 backdrop-blur-md rounded-2xl p-4 flex items-center justify-between shadow-lg">
                    <div className="flex items-center gap-4">
                        <div className="w-12 h-12 rounded-lg bg-gradient-to-br from-emerald-500 to-teal-400 flex items-center justify-center text-white text-lg font-bold">
                            BP
                        </div>
                        <div>
                            <h1 className="text-lg font-semibold text-slate-800">Biblioteca Pública</h1>
                            <div className="text-xs text-slate-500">Catálogo de livros da comunidade</div>
                        </div>
                    </div>
                    <div className="flex items-center gap-3">
                        {usuario ? (
                            <>
                                <div className="text-sm text-slate-700 mr-2">
                                    Olá, <span className="font-medium">{usuario.login}</span>
                                </div>
                                <button
                                    className="px-3 py-1 rounded-md bg-red-500 text-white hover:bg-red-600"
                                    onClick={logout}
                                >
                                    Sair
                                </button>
                            </>
                        ) : (
                            <>
                                <button
                                    className="px-3 py-1 rounded-md border border-slate-200 bg-white hover:shadow-sm"
                                    onClick={() => setMostrarLogin(true)}
                                >
                                    Login
                                </button>
                                <button
                                    className="px-3 py-1 rounded-md bg-emerald-600 text-white hover:bg-emerald-700"
                                    onClick={() => setMostrarCriar(true)}
                                >
                                    Criar conta
                                </button>
                            </>
                        )}
                    </div>
                </div>

                {/* Navegação entre abas */}
                <nav className="mt-4 flex gap-4">
                    <button
                        className={`px-4 py-2 rounded ${abaAtiva === 'livros' ? 'bg-emerald-600 text-white' : 'bg-white border'}`}
                        onClick={() => setAbaAtiva('livros')}
                    >
                        Livros
                    </button>
                    <button
                        className={`px-4 py-2 rounded ${abaAtiva === 'favoritos' ? 'bg-emerald-600 text-white' : 'bg-white border'}`}
                        onClick={() => setAbaAtiva('favoritos')}
                    >
                        Favoritos
                    </button>
                    <button
                        className={`px-4 py-2 rounded ${abaAtiva === 'autores' ? 'bg-emerald-600 text-white' : 'bg-white border'}`}
                        onClick={() => setAbaAtiva('autores')}
                    >
                        Autores
                    </button>
                    <button
                        className={`px-4 py-2 rounded ${abaAtiva === 'editoras' ? 'bg-emerald-600 text-white' : 'bg-white border'}`}
                        onClick={() => setAbaAtiva('editoras')}
                    >
                        Editoras
                    </button>
                </nav>
            </header>

            <main className="mx-auto max-w-6xl px-6 mt-8">
                {renderConteudo()}
            </main>

            {mostrarLogin && (
                <ModalLogin
                    onFechar={() => setMostrarLogin(false)}
                    onSucesso={() => setMostrarLogin(false)}
                />
            )}
            {mostrarCriar && (
                <ModalCriarConta
                    onFechar={() => setMostrarCriar(false)}
                    onSucesso={() => setMostrarCriar(false)}
                />
            )}
        </div>
    );
}
