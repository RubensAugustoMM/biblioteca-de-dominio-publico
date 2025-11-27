package com.bibliotecapublica.servico_biblioteca_publica.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Livro;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioLivro;


@Service
public class ServicoLivro {
    private final RepositorioLivro repositorioLivro;

    @Autowired
    public ServicoLivro ( RepositorioLivro repositorioLivro) {
        this.repositorioLivro = repositorioLivro;
    }
    
    @Transactional
    public Livro salvarLivro(Livro livro) {
        return repositorioLivro.salvar(livro);
    }
}
