package br.com.santebeach.salario;

import java.time.LocalDate;

public class Adiantamento {

    private Funcionario funcionario;
    private LocalDate data;
    private double valor;

    public Adiantamento(Funcionario funcionario, LocalDate data, double valor) {
        this.funcionario = funcionario;
        this.data = data;
        this.valor = valor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public LocalDate getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
}