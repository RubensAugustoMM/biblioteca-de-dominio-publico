package com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositorioUsuario implements iRepositorio<Usuario>{
    @PersistenceContext
    private EntityManager gerenciadorEntidades;

    @Transactional
    @Override
    public List<Usuario> obterTodos() {
        TypedQuery<Usuario> query = gerenciadorEntidades.createQuery("SELECT U FROM Usuario U", Usuario.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Usuario obterPorId(int id) {
        return gerenciadorEntidades.find(Usuario.class, id);
    }

    @Transactional
    @Override
    public void deletarPorId(int id) {
        Usuario usuario = obterPorId(id);
        gerenciadorEntidades.remove(usuario);
    }

    @Transactional
    @Override
    public Usuario salvar(Usuario entidade) {
        if (entidade.getId() == 0) {
            gerenciadorEntidades.persist(entidade);
            return entidade;
        }
        else
            return gerenciadorEntidades.merge(entidade);
    } 

    public Usuario obterPorLogin(String login) {
        if (login == null) return null;
        try {
            TypedQuery<Usuario> q = gerenciadorEntidades.createQuery("SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class);
            q.setParameter("login", login);
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }  
    }
}
