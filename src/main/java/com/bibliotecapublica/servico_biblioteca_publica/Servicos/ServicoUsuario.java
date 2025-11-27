package com.bibliotecapublica.servico_biblioteca_publica.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Usuario;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioUsuario;

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

    public void excluirUsuario(@PathVariable("idUsuario") String idUsuario){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    public void desabilitarUsuario(@PathVariable("idUsuario") String idUsuario){
        throw new UnsupportedOperationException("Unimplemented method 'desabilitarUsuario'");
    }

    public boolean realizarLogin(String idUsuario){
        throw new UnsupportedOperationException("Unimplemented method 'realizarLogin'");
    } 
    //TODO: adicionar mais métodos referentes ao usuário
}
