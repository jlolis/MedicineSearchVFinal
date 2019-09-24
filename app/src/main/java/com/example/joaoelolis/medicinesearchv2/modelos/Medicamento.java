package com.example.joaoelolis.medicinesearchv2.modelos;

public class Medicamento {

    private String nome;
    private String bula;
    private String observacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBula() {
        return bula;
    }

    public void setBula(String bula) {
        this.bula = bula;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Medicamento(String nome, String bula, String observacao) {
        this.nome = nome;
        this.bula = bula;
        this.observacao = observacao;
    }
}
