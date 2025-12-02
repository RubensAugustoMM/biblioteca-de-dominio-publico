package com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.LivroUsuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositorioLivroUsuario implements iRepositorio<LivroUsuario>{
    @PersistenceContext
    private EntityManager gerenciadorEntidades;

    @Transactional
    @Override
    public List<LivroUsuario> obterTodos() {
        TypedQuery<LivroUsuario> query = gerenciadorEntidades.createQuery("SELECT LU FROM LivroUsuario LU",LivroUsuario.class);
        return query.getResultList(); 
    }

    @Transactional
    @Override
    public LivroUsuario obterPorId(int id) {
        return gerenciadorEntidades.find(LivroUsuario.class, id);
    }

    @Transactional
    @Override
    public void deletarPorId(int id) {
        LivroUsuario livroUsuario = obterPorId(id);
        gerenciadorEntidades.remove(livroUsuario);
    }

    @Transactional
    @Override
    public LivroUsuario salvar(LivroUsuario entidade) {
        if (entidade.getId() == 0) {
            gerenciadorEntidades.persist(entidade); 
            return entidade;
        } 
        else
            return gerenciadorEntidades.merge(entidade); 
    }
}
