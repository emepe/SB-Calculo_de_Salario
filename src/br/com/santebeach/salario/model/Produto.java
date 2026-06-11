package br.com.santebeach.salario.model;

public class Produto {

    private String nome;
    private double precoCusto;

    public Produto(String nome, double precoCusto) {
        this.nome = nome;
        this.precoCusto = precoCusto;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }
}