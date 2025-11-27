package com.bibliotecapublica.servico_biblioteca_publica.Controladores;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class ControladorUsuario {
    @GetMapping("/salvar")
    public void salvarUsuario(){
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
    public int realizarLogin(@PathVariable("idUsuario") String idUsuario){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");	
    } 

}
