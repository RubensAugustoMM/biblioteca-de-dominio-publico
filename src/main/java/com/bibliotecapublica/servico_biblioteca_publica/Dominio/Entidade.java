package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

import java.util.Date;
import jakarta.persistence.*;

//Esta é a classe base de todos os tipos de daados do modelo, definindo campos e 
//metodos que todas as entidades devem ter. 
@MappedSuperclass
public abstract class Entidade {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private Date dataCadastro;
    private boolean ativo = true;

    public Entidade() {
        dataCadastro = new Date();
    }

    public int getId(){
        return this.id;
    }

    public Date getDataCadastro(){
        return this.dataCadastro;
    }

    public boolean getAtivo(){
        return this.ativo;
    } 

    public void setAtivo(boolean ativo){
        this.ativo = ativo;
    }
}
