package com.example.quizr;



public class ModelUser {

    private String nome;
    private String email;
    private String senha;
    private int pontos;
    private int numeroJogos;

    public ModelUser (){}

    public ModelUser(String nome, String email, String senha, int pontos , int numeroJogos) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.pontos = pontos = 0;
        this.numeroJogos = numeroJogos = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getNumeroJogos() {
        return numeroJogos;
    }

    public void setNumeroJogos(int numeroJogos) {
        this.numeroJogos = numeroJogos;
    }
}
