package com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Entidade;

public interface iRepositorio<T extends Entidade> {
    public T[] obterTodos();
    public T obterPorId(int id);
    public void deletarPorId(int id);
    public void atualizar(T entidade);
}
