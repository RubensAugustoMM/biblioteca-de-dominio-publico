package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor extends Entidade{
    @Column(nullable = false)
    private String nome;

    public Autor(int id, Date dataCadastro) {
        super(id, dataCadastro);
    }
    
    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}
