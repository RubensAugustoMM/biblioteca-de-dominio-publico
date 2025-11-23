package com.bibliotecapublica.servico_biblioteca_publica.Servicos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bibliotecapublica.servico_biblioteca_publica.Dominio.Livro;
import com.bibliotecapublica.servico_biblioteca_publica.Infra.Repositorios.RepositorioLivro;

@RestController
@RequestMapping("/api/livro")
public class ServicoLivro {
    @GetMapping("/")
    public Livro[] listarLivros(){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    @GetMapping("/favoritar/{idLivro}")
    public void favoritarLivro(@PathVariable("idLivro") String id){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    @GetMapping("/excluir/{idLivro}")
    public void excluirLivro(@PathVariable("idLivro") String id){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }
    //ainda não coloquei o mapping porque é um pouco mais cimplicado pois lida com serialização em json, o que é um saco
    public void adicionarLivro(){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    //ainda não coloquei o mapping porque é um pouco mais cimplicado pois lida com serialização em json, o que é um saco
    public void atualizarLivro(){
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }
}
