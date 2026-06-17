package br.com.santebeach.salario.model;

public class Produto {

    private String nome;
    private double precoCusto;
    private double precoVenda;
    private boolean isCobrado;

    public Produto(String nome, double precoCusto, double precoVenda, boolean isCobrado) {
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.isCobrado = isCobrado;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public boolean isCobrado() {
        return isCobrado;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
}