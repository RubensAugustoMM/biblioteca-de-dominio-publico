package com.bibliotecapublica.servico_biblioteca_publica.Controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Livro;
import com.bibliotecapublica.servico_biblioteca_publica.Servicos.ServicoLivro;

@RestController
@RequestMapping("/api/livro")
public class ControladorLivro {
    private final ServicoLivro servicoLivro;

    public ControladorLivro (ServicoLivro servicoLivro) {
        this.servicoLivro = servicoLivro;
    }

    @GetMapping("/")
    public Livro[] listarLivros(){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    @GetMapping("/teste")
    public ResponseEntity<Livro> criarLivro(){
        Livro livro1 = new Livro();
        livro1.setNome("teste");
        livro1.setGenero("lixo");
        servicoLivro.salvarLivro(livro1);
        return ResponseEntity.status(HttpStatus.CREATED).body(livro1);
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
