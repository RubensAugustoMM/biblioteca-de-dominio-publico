package com.bibliotecapublica.servico_biblioteca_publica.Controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Editora;
import com.bibliotecapublica.servico_biblioteca_publica.Servicos.ServicoEditora;

@RestController
@RequestMapping("/api/editora")
public class ControladorEditora {
    private final ServicoEditora servicoEditora;

    public ControladorEditora(ServicoEditora servicoEditora) {
        this.servicoEditora = servicoEditora;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Editora>> obterTodos() {
        return ResponseEntity.ok(servicoEditora.obterTodos());
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Editora> obterPorId(@PathVariable int id) {
        return ResponseEntity.ok(servicoEditora.obterPorId(id));
    }

    @PostMapping("/salvar")
    public ResponseEntity<Editora> salvar(@RequestBody Editora editora) {
        Editora salvo = servicoEditora.salvar(editora);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @DeleteMapping("/excluir/{id:\\d+}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        servicoEditora.excluir(id);
        return ResponseEntity.noContent().build();
    }
}