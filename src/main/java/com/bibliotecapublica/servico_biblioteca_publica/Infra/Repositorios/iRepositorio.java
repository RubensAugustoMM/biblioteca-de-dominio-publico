package com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Entidade;

//sim, seu sei que tem o JPARepostory do hibernate, esta interface existe para demonstrar o princípio do polimorfismo

public interface iRepositorio<T extends Entidade> {
    public T salvar(T entidade);
    public T[] obterTodos();
    public T obterPorId(int id);
    public void deletarPorId(int id);
}
