package br.com.santebeach.salario.model;

import java.time.LocalDate;

public class Adiantamento implements RegraDeDesconto {

    private Funcionario funcionario;
    private LocalDate data;
    private double valor;
    private String categoria;

    public Adiantamento(Funcionario funcionario, LocalDate data, double valor) {
        this.funcionario = funcionario;
        this.data = data;
        this.valor = valor;
        this.categoria = "Adiantamento";
    }

    @Override
    public Funcionario getFuncionario() {
        return funcionario;
    }

    @Override
    public LocalDate getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public double getValorParaDesconto() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }
}