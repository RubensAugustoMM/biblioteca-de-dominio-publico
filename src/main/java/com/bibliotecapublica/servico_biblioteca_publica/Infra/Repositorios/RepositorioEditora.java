package com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Editora;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositorioEditora implements iRepositorio<Editora> {
    @PersistenceContext
    private EntityManager gerenciadorEntidades;

    @Transactional
    @Override
    public List<Editora> obterTodos() {
        TypedQuery<Editora> query = gerenciadorEntidades.createQuery("SELECT E FROM Editora E", Editora.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Editora obterPorId(int id) {
        return gerenciadorEntidades.find(Editora.class, id);
    }

    @Transactional
    @Override
    public void deletarPorId(int id) {
        Editora editora = obterPorId(id);
        gerenciadorEntidades.remove(editora);
    }

    @Transactional
    @Override
    public Editora salvar(Editora entidade) {
        if (entidade.getId() == 0) {
            gerenciadorEntidades.persist(entidade);
            return entidade;
        }
        else 
            return gerenciadorEntidades.merge(entidade);
    }
}
