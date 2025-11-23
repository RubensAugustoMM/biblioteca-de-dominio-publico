package com.bibliotecapublica.servico_biblioteca_publica.Servicos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class ServicoUsuario {
    @GetMapping("/criar")
    public void criarUsuario(){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    @GetMapping("/excluir/{idUsuario}")
    public void excluirUsuario(@PathVariable("idUsuario") String idUsuario){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    @GetMapping("/desabilitar/{idUsuario}")
    public void desabilitarUsuario(@PathVariable("idUsuario") String idUsuario){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    @GetMapping("/login/{idUsuario}")
    public String realizarLogin(@PathVariable("idUsuario") String idUsuario){
        return "teste";
    } 
    //TODO: adicionar mais métodos referentes ao usuário
}
