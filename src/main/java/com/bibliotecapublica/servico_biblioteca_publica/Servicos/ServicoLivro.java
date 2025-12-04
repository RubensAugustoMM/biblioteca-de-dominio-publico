package com.bibliotecapublica.servico_biblioteca_publica.Servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Autor;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Editora;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Livro;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.LivroUsuario;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.TipoLivroUsuario;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Usuario;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioAutor;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioEditora;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioLivro;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioLivroUsuario;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioUsuario;

@Service
public class ServicoLivro {
    private final RepositorioLivro repositorioLivro;
    private final RepositorioAutor repositorioAutor;
    private final RepositorioEditora repositorioEditora;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioLivroUsuario repositorioLivroUsuario;

    @Autowired
    public ServicoLivro(
        RepositorioLivro repositorioLivro,
        RepositorioEditora repositorioEditora,
        RepositorioAutor repositorioAutor,
        RepositorioUsuario repositorioUsuario,
        RepositorioLivroUsuario repositorioLivroUsuario
    ) {
        this.repositorioLivro = repositorioLivro;
        this.repositorioEditora = repositorioEditora;
        this.repositorioAutor = repositorioAutor;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioLivroUsuario = repositorioLivroUsuario;
    }

    @Transactional
    public Editora salvarEditora(Editora editora) {
        return repositorioEditora.salvar(editora);
    }

    @Transactional
    public Autor salvarAutor(Autor autor) {
        return repositorioAutor.salvar(autor);
    }

    @Transactional
    public void excluirAutor(int id) {
        
    }

    @Transactional 
    public Livro obterPorId(int id) {
        return this.repositorioLivro.obterPorId(id);
    } 

    @Transactional
    public Livro salvar(Livro livro) {
        return repositorioLivro.salvar(livro);
    }

    @Transactional(readOnly = true)
    public List<Livro> obterTodos() {
        return repositorioLivro.obterTodos();
    }

    @Transactional
    public void excluir(int id) {
        repositorioLivro.deletarPorId(id);
    }

@Transactional
public void favoritar(int idLivro, int idUsuario){
    Livro livro = repositorioLivro.obterPorId(idLivro);
    Usuario usuario = repositorioUsuario.obterPorId(idUsuario);

    LivroUsuario livroUsuario = new LivroUsuario();
    livroUsuario.setLivro(livro);
    livroUsuario.setUsuario(usuario);
    livroUsuario.setTipo(TipoLivroUsuario.Favorito);

    repositorioLivroUsuario.salvar(livroUsuario);
}
}