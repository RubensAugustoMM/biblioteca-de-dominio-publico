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
public boolean realizarLogin(Usuario credenciais) {
    if (credenciais == null || credenciais.getLogin() == null || credenciais.getSenha() == null) {
        return false;
    }

    Usuario usuarioBanco = this.repositorioUsuario.obterPorLogin(credenciais.getLogin());
    if (usuarioBanco == null) {
        return false;
    }

    String hashBanco = usuarioBanco.getSenha();
    if (hashBanco == null || hashBanco.isEmpty()) {
        return false;
    }

    try {
        boolean ok = BCrypt.checkpw(credenciais.getSenha(), hashBanco);
        return ok;
    } catch (Exception ex) {
        System.err.println("Erro verificando senha para login=" + credenciais.getLogin() + " : " + ex.getMessage());
        return false;
    }
}    

    public List<Livro> obterLivrosDoUsuarioPorId(int id, boolean somenteFavoritos) {
        LivroUsuario[] livrosUsuarios = repositorioLivroUsuario.obterTodos().toArray(new LivroUsuario[0]);

        Stream<LivroUsuario> livroUsuarioStream = Arrays.stream(livrosUsuarios).filter(x -> x.getUsuario().getId() == id);
        if(somenteFavoritos)
            livroUsuarioStream = livroUsuarioStream.filter(x -> x.getTipo() == TipoLivroUsuario.Favorito);
        return livroUsuarioStream.map(x -> x.getLivro()).toList();
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
