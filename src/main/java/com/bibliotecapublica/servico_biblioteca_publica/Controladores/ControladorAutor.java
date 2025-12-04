package com.bibliotecapublica.servico_biblioteca_publica.Controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Autor;
import com.bibliotecapublica.servico_biblioteca_publica.Servicos.ServicoAutor;

@RestController
@RequestMapping("/api/autor")
public class ControladorAutor {
    private final ServicoAutor servicoAutor;

    public ControladorAutor(ServicoAutor servicoAutor) {
        this.servicoAutor = servicoAutor;
    }

    @GetMapping("todos")
    public ResponseEntity<List<Autor>> obterTodos() {
        return ResponseEntity.ok(servicoAutor.obterTodos());
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Autor> obter(@PathVariable int id) {
        return ResponseEntity.ok(servicoAutor.obterPorId(id));
    }

    @PostMapping("salvar")
    public ResponseEntity<Autor> salvar(@RequestBody Autor autor) {
        Autor salvo = servicoAutor.salvar(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @DeleteMapping("/excluir/{id:\\d+}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        servicoAutor.excluir(id);
        return ResponseEntity.noContent().build();
    }
}