package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

import java.util.Date;

public class LivroUsuario extends Entidade{
    private int idLivro;
    private Livro livro;
    private int idUsuario;
    private Usuario usuario;
    private TipoLivroUsuario tipo;

    public LivroUsuario(int id, Date dataCadastro) {
        super(id, dataCadastro);
        //TODO Auto-generated constructor stub
    }

    public int getIdLivro() {
        return idLivro;
    }

    public Livro getLivro() {
        return livro;
    }

    //TODO: criar uma excessao propria para o caso do id for invalido
    public void setLivro(Livro livro) throws Exception{
        if(livro.getId() == 0)
            throw new Exception("idLivro invalido!");
        this.idLivro = livro.getId();
        this.livro = livro; 
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    //TODO: criar uma excessao propria para o caso do id for invalido
    public void setUsuario(Usuario usuario) throws Exception{
        if(usuario.getId() == 0)
            throw new Exception("idUsuario invalido!");
        this.idUsuario = livro.getId();
        this.usuario = usuario;
    }

    public TipoLivroUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoLivroUsuario tipo) {
        this.tipo = tipo;
    }
}