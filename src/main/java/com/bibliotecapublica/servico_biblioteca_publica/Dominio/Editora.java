package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "editoras")
public class Editora extends Entidade{
    @Column(nullable = false)
    private String nome;

    public Editora(int id, Date dataCadastro) {
        super(id, dataCadastro);
        //TODO Auto-generated constructor stub
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

}
