package com.example.joaoelolis.medicinesearchv2.modelos;

public class Medicamento {

    private String nome;
    private String bula;
    private String observacao;

    public Medicamento(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toLowerCase();
    }

    public String getBula() {
        return bula;
    }

    public void setBula(String bula) {
        this.bula = bula.toLowerCase();
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao.toLowerCase();
    }



    public Medicamento(String nome, String bula, String observacao) {
        this.nome = nome.toLowerCase();
        this.bula = bula.toLowerCase();
        this.observacao = observacao.toLowerCase();
    }
}
