package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.*;

@Entity
@Table(name = "livros_usuarios")
public class LivroUsuario extends Entidade{
    @ManyToAny
    @JoinColumn(name = "id_livro")
    private Livro livro;

    @ManyToAny
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private TipoLivroUsuario tipo;

    public LivroUsuario(int id, Date dataCadastro) {
        super(id, dataCadastro);
        //TODO Auto-generated constructor stub
    }

    public Livro getLivro() {
        return livro;
    }

    //TODO: criar uma excessao propria para o caso do id for invalido
    public void setLivro(Livro livro) throws Exception{
        if(livro.getId() == 0)
            throw new Exception("idLivro invalido!");
        this.livro = livro; 
    }

    public Usuario getUsuario() {
        return usuario;
    }

    //TODO: criar uma excessao propria para o caso do id for invalido
    public void setUsuario(Usuario usuario) throws Exception{
        if(usuario.getId() == 0)
            throw new Exception("idUsuario invalido!");
        this.usuario = usuario;
    }

    public TipoLivroUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoLivroUsuario tipo) {
        this.tipo = tipo;
    }
}