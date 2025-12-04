package com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Livro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositorioLivro implements iRepositorio<Livro>{
    @PersistenceContext
    private EntityManager gerenciadorEntidades;

    @Transactional
    @Override
    public List<Livro> obterTodos() {
        TypedQuery<Livro> query = gerenciadorEntidades.createQuery("SELECT L FROM Livro L", Livro.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Livro obterPorId(int id) {
        return gerenciadorEntidades.find(Livro.class, id);
    }

    @Transactional
    @Override
    public void deletarPorId(int id) {
        Livro livro = obterPorId(id);
        gerenciadorEntidades.remove(livro);
    }

    @Transactional
    @Override
    public Livro salvar(Livro entidade) {
        if (entidade.getId() == 0) {
            gerenciadorEntidades.persist(entidade);
            return entidade;
        }
        else 
            return gerenciadorEntidades.merge(entidade);
    }
}
