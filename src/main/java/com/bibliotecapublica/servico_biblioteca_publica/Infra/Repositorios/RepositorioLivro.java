package com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Livro;

public class RepositorioLivro implements iRepositorio<Livro>{

    @Override
    public Livro[] obterTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    @Override
    public Livro obterPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterPorId'");
    }

    @Override
    public void deletarPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarPorId'");
    }

    @Override
    public void atualizar(Livro entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }
}
