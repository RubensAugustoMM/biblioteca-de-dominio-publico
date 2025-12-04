package com.bibliotecapublica.servico_biblioteca_publica.Controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Livro;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Usuario;
import com.bibliotecapublica.servico_biblioteca_publica.Servicos.ServicoUsuario;

@RestController
@RequestMapping("/api/usuario")
public class ControladorUsuario {
    private final ServicoUsuario servicoUsuario;

    public ControladorUsuario(ServicoUsuario servicoUsuario) {
        this.servicoUsuario = servicoUsuario;
    }

    @PostMapping(value = "/salvar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
        Usuario salvo = this.servicoUsuario.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @DeleteMapping("/excluir/{id:\\d+}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable("id") int id) {
        this.servicoUsuario.excluir(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> realizarLogin(@RequestBody Usuario credenciais) {
        try {
            boolean ok = servicoUsuario.realizarLogin(credenciais);
            if (ok)
                return ResponseEntity.ok().build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        return ResponseEntity.ok(this.servicoUsuario.obterTodosUsuarios());
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Usuario> obterPorId(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.servicoUsuario.obterPorId(id));
    }

    @GetMapping("/obterfavoritos/{id}")
    public ResponseEntity<List<Livro>> obterFavoritos(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.servicoUsuario.obterLivrosDoUsuarioPorId(id, false));
    }
}
