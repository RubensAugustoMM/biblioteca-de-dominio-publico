package com.bibliotecapublica.servico_biblioteca_publica.Controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Livro;
import com.bibliotecapublica.servico_biblioteca_publica.Servicos.ServicoLivro;

@RestController
@RequestMapping("/api/livro")
public class ControladorLivro {
    private final ServicoLivro servicoLivro;

    public ControladorLivro(ServicoLivro servicoLivro) {
        this.servicoLivro = servicoLivro;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Livro>> obterTodos() {
        return ResponseEntity.ok(servicoLivro.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> obterPorId(@PathVariable int id) {
        return ResponseEntity.ok(servicoLivro.obterPorId(id));
    }

    @PostMapping("/salvar")
    public ResponseEntity<Livro> salvar(@RequestBody Livro livro) {
        Livro salvo = servicoLivro.salvar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PostMapping("/favoritar/{idLivro}/{idUsuario}")
    public ResponseEntity<Void> favoritarLivro(
            @PathVariable int idLivro,
            @PathVariable int idUsuario) {
            
        servicoLivro.favoritar(idLivro, idUsuario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/excluir/{idLivro}")
    public ResponseEntity<Void> excluirLivro(@PathVariable int idLivro) {
        servicoLivro.excluir(idLivro);
        return ResponseEntity.noContent().build();
    }
}