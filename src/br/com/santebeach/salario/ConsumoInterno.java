package br.com.santebeach.salario;

import java.time.LocalDate;

public class ConsumoInterno {

    private Funcionario funcionario;
    private Produto produto;
    private LocalDate data;
    private int quantidade;

    public ConsumoInterno(Funcionario funcionario, Produto produto, LocalDate data, int quantidade) {
        this.funcionario = funcionario;
        this.produto = produto;
        this.data = data;
        this.quantidade = quantidade;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Produto getProduto() {
        return produto;
    }

    public LocalDate getData() {
        return data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorCustoTotal() {
        return produto.getPrecoCusto() * quantidade;
    }

    public double getValorParaDesconto() {
        return getValorCustoTotal() * 1.03;
    }
}