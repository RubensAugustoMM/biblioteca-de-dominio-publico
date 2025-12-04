package com.bibliotecapublica.servico_biblioteca_publica.Servicos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Editora;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioEditora;

@Service
public class ServicoEditora {
    private final RepositorioEditora repositorioEditora;

    public ServicoEditora(RepositorioEditora repositorioEditora) {
        this.repositorioEditora = repositorioEditora;
    }

    @Transactional(readOnly = true)
    public List<Editora> obterTodos() {
        return repositorioEditora.obterTodos();
    }

    @Transactional(readOnly = true)
    public Editora obterPorId(int id) {
        Editora e = repositorioEditora.obterPorId(id);
        if (e == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Editora não encontrada");
        return e;
    }

    @Transactional
    public Editora salvar(Editora editora) {
        return repositorioEditora.salvar(editora);
    }

    @Transactional
    public void excluir(int id) {
        Editora existente = repositorioEditora.obterPorId(id);
        if (existente == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Editora não encontrada");
        repositorioEditora.deletarPorId(id);
    }
}