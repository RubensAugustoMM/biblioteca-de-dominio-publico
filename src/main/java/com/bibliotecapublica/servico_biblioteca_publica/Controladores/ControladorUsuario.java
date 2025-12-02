package com.bibliotecapublica.servico_biblioteca_publica.Controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Usuario;
import com.bibliotecapublica.servico_biblioteca_publica.Servicos.ServicoUsuario;

@RestController
@RequestMapping("/api/usuario")
public class ControladorUsuario {
    private final ServicoUsuario servicoUsuario;

    public ControladorUsuario(ServicoUsuario servicoUsuario) {
        this.servicoUsuario = servicoUsuario;
    }

    @PostMapping("/salvar")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(this.servicoUsuario.salvar(usuario));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable("id") int id){
        this.servicoUsuario.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login/{id}")
    public ResponseEntity<Void> realizarLogin(@PathVariable("id") int id){
        if(this.servicoUsuario.realizarLogin(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } 

    @GetMapping("/todos")
    public ResponseEntity<Usuario[]> obterTodosUsuarios() {
        return ResponseEntity.ok(this.servicoUsuario.obterTodosUsuarios().toArray(new Usuario[0]));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterPorId(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.servicoUsuario.obterPorId(id));
    }
}
