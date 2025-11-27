package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro extends Entidade{
    @Column(nullable = false)
    private String nome;

    @Column(name = "data_lancamento")
    private Date dataLancamento;

    @Column(nullable = false)
    private String genero;

    @ManyToOne
    @JoinColumn(name="id_editora")
    private Editora editora;

    @ManyToOne
    @JoinColumn(name="id_autor")
    private Autor autor;

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public Date getDataLancamento(){
        return this.dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento){
        this.dataLancamento = dataLancamento;
    }   
 
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }   
 
    public Editora getEditora() {
        return editora;
    }

    //TODO: criar uma excessao propria para o caso do id for invalido
    public void setEditora(Editora editora) throws Exception {
        if(editora.getId() == 0)
            throw new Exception("idEditora invalido!");
        this.editora = editora;
    }

    public Autor getAutor() {
        return autor;
    }

    //TODO: criar uma excessao propria para o caso do id for invalido
    public void setAutor(Autor autor) throws Exception{
        if(autor.getId() == 0)
            throw new Exception("idAutor invalido!");
        this.autor = autor;
    }
}
