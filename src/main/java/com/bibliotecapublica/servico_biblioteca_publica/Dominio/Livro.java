package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

import java.util.Date;

public class Livro extends Entidade{
    private String nome; 
    private Date dataLancamento;
    private String genero; 
    private int idEditora;
    private Editora editora;
    private int idAutor;
    private Autor autor;

    public Livro(int id, Date dataCadastro) {
        super(id, dataCadastro);
    }

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

    public int getIdEditora() {
        return idEditora;
    }
 
    public Editora getEditora() {
        return editora;
    }

    //TODO: criar uma excessao propria para o caso do id for invalido
    public void setEditora(Editora editora) throws Exception {
        if(editora.getId() == 0)
            throw new Exception("idEditora invalido!");
        this.idEditora = editora.getId();
        this.editora = editora;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public Autor getAutor() {
        return autor;
    }

    //TODO: criar uma excessao propria para o caso do id for invalido
    public void setAutor(Autor autor) throws Exception{
        if(autor.getId() == 0)
            throw new Exception("idAutor invalido!");
        this.idAutor = autor.getId();
        this.autor = autor;
    }
}
