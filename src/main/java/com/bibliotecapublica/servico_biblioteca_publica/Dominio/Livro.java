package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "livros")
public class Livro extends Entidade {
    @Column(nullable = false)
    private String nome;

    @Column(name = "data_lancamento")
    private OffsetDateTime dataLancamento;

    @Column(nullable = false)
    private String genero;

    @ManyToOne
    @JoinColumn(name="id_editora")
    private Editora editora;

    @ManyToOne
    @JoinColumn(name="id_autor")
    private Autor autor;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public OffsetDateTime getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(OffsetDateTime dataLancamento) { this.dataLancamento = dataLancamento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Editora getEditora() { return editora; }
    public void setEditora(Editora editora) throws Exception {
        if (editora.getId() == 0) throw new Exception("idEditora invalido!");
        this.editora = editora;
    }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) throws Exception {
        if (autor.getId() == 0) throw new Exception("idAutor invalido!");
        this.autor = autor;
    }
}