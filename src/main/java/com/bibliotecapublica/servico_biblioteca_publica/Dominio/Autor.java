package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

import java.util.Date;

public class Autor extends Entidade{
    public Autor(int id, Date dataCadastro) {
        super(id, dataCadastro);
    }

    private String nome;

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}
