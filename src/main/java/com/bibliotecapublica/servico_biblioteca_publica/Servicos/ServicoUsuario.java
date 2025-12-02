package com.bibliotecapublica.servico_biblioteca_publica.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Usuario;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioUsuario;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class ServicoUsuario {
    private final RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicoUsuario (RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }
    public void salvarUsuario(Usuario usuario){
        repositorioUsuario.salvar(usuario);
    }

    public void excluir(int id){
        this.repositorioUsuario.deletarPorId(id);
    }

    public void salvar(Usuario usuario){
        String salt = BCrypt.gensalt(); 
        String senhaCriptografada = BCrypt.hashpw(usuario.getSenha(), salt);
        usuario.setSenhaSalt(salt); 
        usuario.setSenha(senhaCriptografada);
        this.repositorioUsuario.salvar(usuario);
    }

    public void desabilitarUsuario(int id){
        Usuario usuario = this.repositorioUsuario.obterPorId(id);
        usuario.setAtivo(false);
        this.repositorioUsuario.salvar(usuario);
    }

    public void ativarUsuario(int id) {
        Usuario usuario = this.repositorioUsuario.obterPorId(id);
        usuario.setAtivo(true);
        this.repositorioUsuario.salvar(usuario);
    }

    public Usuario[] obterTodosUsuarios(){
        return this.repositorioUsuario.obterTodos();
    }

    public boolean realizarLogin(Usuario usuario){
        Usuario[] usuarios = this.repositorioUsuario.obterTodos();
        for(Usuario usr : usuarios) {
            if(usr.getLogin().equals(usuario.getLogin()) 
               && BCrypt.checkpw(usuario.getSenha(), usr.getSenha())) {
                return true;
               }
        }
        return false;
    }    
}
