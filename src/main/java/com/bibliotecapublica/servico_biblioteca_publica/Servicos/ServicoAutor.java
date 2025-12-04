package com.bibliotecapublica.servico_biblioteca_publica.Servicos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Autor;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioAutor;

@Service
public class ServicoAutor {
    private final RepositorioAutor repositorioAutor;

    public ServicoAutor(RepositorioAutor repositorioAutor) {
        this.repositorioAutor = repositorioAutor;
    }

    @Transactional(readOnly = true)
    public List<Autor> obterTodos() {
        return repositorioAutor.obterTodos();
    }

    @Transactional(readOnly = true)
    public Autor obterPorId(int id) {
        Autor a = repositorioAutor.obterPorId(id);
        if (a == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado");
        return a;
    }

    @Transactional
    public Autor salvar(Autor autor) {
        return repositorioAutor.salvar(autor);
    }

    @Transactional
    public void excluir(int id) {
        Autor existente = repositorioAutor.obterPorId(id);
        if (existente == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado");
        repositorioAutor.deletarPorId(id);
    }
}