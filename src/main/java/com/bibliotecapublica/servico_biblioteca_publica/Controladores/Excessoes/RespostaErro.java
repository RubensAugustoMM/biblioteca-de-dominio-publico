package com.bibliotecapublica.servico_biblioteca_publica.Controladores.Excessoes;

import java.util.Date;

public class RespostaErro {
    private String mensagemErro;
    private String caminho;
    public String codigo;
    public String traceId;
    private Date dataCadastro = new Date();

    public String getMensagemErro() {
        return this.mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro; 
    }

    public String getCaminho() {
        return this.caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTraceId() {
        return this.codigo;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    } 

}
