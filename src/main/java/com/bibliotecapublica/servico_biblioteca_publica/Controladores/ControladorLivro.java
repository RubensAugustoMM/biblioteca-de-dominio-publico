package com.bibliotecapublica.servico_biblioteca_publica.Controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Livro;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Usuario;
import com.bibliotecapublica.servico_biblioteca_publica.Servicos.ServicoLivro;
import com.bibliotecapublica.servico_biblioteca_publica.Servicos.ServicoUsuario;

@RestController
@RequestMapping("/api/livro")
public class ControladorLivro {
    private final ServicoUsuario servicoLivro;

    public ControladorLivro (ServicoUsuario servicoLivro) {
        this.servicoLivro = servicoLivro;
    }

    @GetMapping("/")
    public Livro[] listarLivros(){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    @GetMapping("/teste")
    public ResponseEntity<Usuario> teste(){
        Usuario usuario = new Usuario();
        usuario.setNome("teste");
        usuario.setLogin("teste");
        usuario.setSenha("teste");
        usuario.setEmail("teste");
        servicoLivro.salvarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> criarLivro(){
        throw new UnsupportedOperationException("Unimplemented method 'criarLivros'");
    }

    @GetMapping("/favoritar/{idLivro}")
    public void favoritarLivro(@PathVariable("idLivro") String id){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    @GetMapping("/excluir/{idLivro}")
    public void excluirLivro(@PathVariable("idLivro") String id){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }
}
