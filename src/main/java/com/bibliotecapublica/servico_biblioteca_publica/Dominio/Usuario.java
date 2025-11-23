package com.bibliotecapublica.servico_biblioteca_publica.Dominio;

public class Usuario extends Entidade{
    private String login;
    private String nome;
    private String cpf;
    private String senha;
    private String email;
    private int idade;

    public Usuario(int id, java.util.Date dataCadastro) {
        super(id, dataCadastro);
        //TODO Auto-generated constructor stub
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}

