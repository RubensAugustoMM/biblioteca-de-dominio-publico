package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

import java.util.Date;

public class UsuarioAdm extends Usuario {
    //TODO: transformar rank em enum
    private String rank;
    private String convite;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getConvite() {
        return convite;
    }

    public void setConvite(String convite) {
        this.convite = convite;
    }
}
