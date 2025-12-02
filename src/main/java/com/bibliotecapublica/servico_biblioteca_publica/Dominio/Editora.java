package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "editoras")
public class Editora extends Entidade{
    @Column(nullable = false)
    private String nome;

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

}
