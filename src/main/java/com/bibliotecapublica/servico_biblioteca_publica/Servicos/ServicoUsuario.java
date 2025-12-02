package com.bibliotecapublica.servico_biblioteca_publica.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Livro;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.LivroUsuario;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.TipoLivroUsuario;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Usuario;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioLivro;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioLivroUsuario;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioUsuario;

import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class ServicoUsuario {
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioLivro repositorioLivro;
    private final RepositorioLivroUsuario repositorioLivroUsuario;

    @Autowired
    public ServicoUsuario ( RepositorioUsuario repositorioUsuario
                          , RepositorioLivro repositorioLivro
                          , RepositorioLivroUsuario repositorioLivroUsuario
    ) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioLivro = repositorioLivro;
        this.repositorioLivroUsuario = repositorioLivroUsuario;
    }

    @Transactional
    public void excluir(int id){
        this.repositorioUsuario.deletarPorId(id);
    }

    @Transactional
    public Usuario salvar(Usuario usuario){
        String salt = BCrypt.gensalt(); 
        String senhaCriptografada = BCrypt.hashpw(usuario.getSenha(), salt);
        usuario.setSenhaSalt(salt); 
        usuario.setSenha(senhaCriptografada);
        return this.repositorioUsuario.salvar(usuario);
    }

    @Transactional
    public void desabilitarUsuario(int id){
        Usuario usuario = this.repositorioUsuario.obterPorId(id);
        usuario.setAtivo(false);
        this.repositorioUsuario.salvar(usuario);
    }

    @Transactional
    public void ativarUsuario(int id) {
        Usuario usuario = this.repositorioUsuario.obterPorId(id);
        usuario.setAtivo(true);
        this.repositorioUsuario.salvar(usuario);
    }

    @Transactional
    public List<Usuario> obterTodosUsuarios(){
        return this.repositorioUsuario.obterTodos();
    }

    @Transactional
    public boolean realizarLogin(int id){
        List<Usuario> usuarios = this.repositorioUsuario.obterTodos();
        Usuario usuario = this.repositorioUsuario.obterPorId(id); 
        for(Usuario usr : usuarios) {
            if(verificarSenha(usuario, usr))
                return true;
        }
        return false;
    }    

    public Livro[] obterLivrosDoUsuarioPorId(int id, boolean somenteFavoritos) {
        LivroUsuario[] livrosUsuarios = repositorioLivroUsuario.obterTodos().toArray(new LivroUsuario[0]);

        Stream<LivroUsuario> livroUsuarioStream = Arrays.stream(livrosUsuarios).filter(x -> x.getUsuario().getId() == id);
        if(somenteFavoritos)
            livroUsuarioStream = livroUsuarioStream.filter(x -> x.getTipo() == TipoLivroUsuario.Favorito);
        return (Livro[])livroUsuarioStream.map(x -> x.getLivro()).toArray();
    }

    private boolean verificarSenha(Usuario inserido, Usuario banco) {
        if(!banco.getLogin().equals(inserido.getLogin())) 
            return false;
        if(!BCrypt.checkpw(inserido.getSenha(), banco.getSenha())) 
            return false; 
        return true;
    }

    public Usuario obterPorId(int id) {
        return this.repositorioUsuario.obterPorId(id);
    }
}
