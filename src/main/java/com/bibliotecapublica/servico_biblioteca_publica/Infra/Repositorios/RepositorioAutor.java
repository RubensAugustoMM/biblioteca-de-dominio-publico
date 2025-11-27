package com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios;

import org.springframework.stereotype.Repository;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Autor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositorioAutor implements iRepositorio<Autor>{

    @PersistenceContext
    private EntityManager gerenciadorEntidades;

    //como pode-se ver este é o unico método que diferencia dos outros repositórios
    //com isso poderia-se tornar iRepositorio uma classe abstrata genérica e os repositórios só implementariam o método obterTodos
    //todavia, classes genéricas em java não são verdadeiramente genéricas como em C#, tendo que fazer umas gambiarras com Class<T>
    //que eu não sei se estou a fim de fazer no momento
    

    @Transactional
    @Override
    public Autor[] obterTodos() {
        TypedQuery<Autor> query = gerenciadorEntidades.createQuery("SELECT A FROM Autor A", Autor.class);
        return (Autor[])query.getResultList().toArray();
    }

    @Transactional
    @Override
    public Autor obterPorId(int id) {
        return gerenciadorEntidades.find(Autor.class, id);
    }

    @Transactional
    @Override
    public void deletarPorId(int id) {
        Autor autor = obterPorId(id);
        if (autor != null)
            gerenciadorEntidades.remove(autor);
    }

    @Transactional
    @Override
    public Autor salvar(Autor entidade) {
        if (entidade.getId() == 0) {
            gerenciadorEntidades.persist(entidade);
            return entidade;
        }
        else {
            return gerenciadorEntidades.merge(entidade);
        }
    }
}
