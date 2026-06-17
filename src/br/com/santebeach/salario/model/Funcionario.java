package br.com.santebeach.salario.model;

import java.util.List;

public class Funcionario {

    private String nome;
    private double salarioBase;
    private int diasASeremTrabalhados;

    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.diasASeremTrabalhados = 28;
    }

    public int getDiasASeremTrabalhados() {
        return diasASeremTrabalhados;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setDiasASeremTrabalhados(int Dias) {
        this.diasASeremTrabalhados = Dias;
    }
}