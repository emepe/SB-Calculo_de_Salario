package br.com.santebeach.salario.model;

import java.time.LocalDate;

public class ConsumoInterno implements RegraDeDesconto {

    private Funcionario funcionario;
    private Produto produto;
    private LocalDate data;
    private int quantidade;
    private String categoria;

    public ConsumoInterno(Funcionario funcionario, Produto produto, LocalDate data, int quantidade) {
        this.funcionario = funcionario;
        this.produto = produto;
        this.data = data;
        this.quantidade = quantidade;
        this.categoria = "Consumo";
    }

    @Override
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public LocalDate getData() {
        return data;
    }

    @Override
    public double getPrecoCusto() {
        return produto.getPrecoCusto();
    }

    @Override
    public double getPrecoVenda() {
        return produto.getPrecoVenda();
    }

    @Override
    public boolean isCobrado() {
        return produto.isCobrado();
    }

    @Override
    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String getCategoria() {
        return categoria;
    }

    @Override
    public double getValorParaDesconto() {
        return getPrecoCusto();
    }
}