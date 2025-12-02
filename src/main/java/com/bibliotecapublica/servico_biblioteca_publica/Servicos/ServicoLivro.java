package com.bibliotecapublica.servico_biblioteca_publica.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Autor;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Editora;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Livro;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioAutor;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioEditora;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioLivro;


@Service
public class ServicoLivro {
    private final RepositorioLivro repositorioLivro;
    private final RepositorioAutor repositorioAutor;
    private final RepositorioEditora repositorioEditora;

    @Autowired
    public ServicoLivro ( RepositorioLivro repositorioLivro
                        , RepositorioEditora repositorioEditora
                        , RepositorioAutor repositorioAutor
    ) {
        this.repositorioLivro = repositorioLivro;
        this.repositorioEditora = repositorioEditora;
        this.repositorioAutor = repositorioAutor;
    }

    @Transactional
    public void salvarEditora(Editora editora) {
        this.repositorioEditora.salvar(editora);
    }

    @Transactional
    public void salvarAutor(Autor autor) {
        this.repositorioAutor.salvar(autor);
    }
    
    @Transactional
    public Livro salvarLivro(Livro livro) {
        return repositorioLivro.salvar(livro);
    }
}
