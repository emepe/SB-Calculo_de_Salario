package br.com.santebeach.salario.service;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import br.com.santebeach.salario.model.Funcionario;
import br.com.santebeach.salario.model.RegraDeDesconto;

public class CalculadoraSalarioService {

    public double limiteConsumoMensal(int Dias) {
        double consumoDiario = 36.00;
        return consumoDiario * Dias;
    }

    public double calcularAdiantamentos(List<RegraDeDesconto> descontos, Funcionario Funcionario, Month mes, int ano) {
        return descontos.stream()
                .filter(d -> d.getData().getMonth() == mes &&
                        d.getData().getYear() == ano &&
                        d.getCategoria() == "Adiantamento" &&
                        d.getFuncionario().equals(Funcionario))
                .mapToDouble(RegraDeDesconto::getPrecoCusto)
                .sum();
    }

    public double calcularConsumoInternoCobraveis(List<RegraDeDesconto> descontos, Funcionario Funcionario, Month mes,
            int ano) {
        return descontos.stream()
                .filter(d -> d.getData().getMonth() == mes &&
                        d.getData().getYear() == ano &&
                        d.getCategoria() == "Consumo" &&
                        d.getFuncionario().equals(Funcionario) &&
                        d.isCobrado() == true)
                .mapToDouble(d -> d.getPrecoCusto() * d.getQuantidade())
                .sum();
    }

    public double calcularConsumoInternoNotCobraveis(List<RegraDeDesconto> descontos,Funcionario Funcionario, Month mes, int ano) {
        double ValorTotal = 0.0;
        double limiteConsumo = limiteConsumoMensal(Funcionario.getDiasASeremTrabalhados());
        for (RegraDeDesconto desconto : descontos) {
            if (desconto.getData().getMonth() == mes && 
            desconto.getData().getYear() == ano && 
            desconto.getCategoria().equals("Consumo") && 
            desconto.getFuncionario().equals(Funcionario) && 
            desconto.isCobrado() == false) {
                for(int i = 0; i < desconto.getQuantidade(); i++) {
                    if (ValorTotal < limiteConsumo) {
                        ValorTotal += desconto.getPrecoVenda();
                    }
                    else {
                        ValorTotal += desconto.getPrecoCusto();
                    }
                }
            }
        }

        return ValorTotal;
    }

    public double calcularConsumoInternoTotal(List<RegraDeDesconto> descontos,Funcionario Funcionario, Month mes, int ano) {
        double consumoInternoCobraveis = calcularConsumoInternoCobraveis(descontos, Funcionario, mes, ano);
        double consumoInternoNotCobraveis = calcularConsumoInternoNotCobraveis(descontos, Funcionario, mes, ano);
        double limiteConsumo = limiteConsumoMensal(Funcionario.getDiasASeremTrabalhados());
        if (consumoInternoNotCobraveis <= limiteConsumo) {
            return (consumoInternoCobraveis * 1.03);
        }

        return (((consumoInternoNotCobraveis - limiteConsumo) +  consumoInternoCobraveis) * 1.03);
    }

    public double calcularTotalDescontos(List<RegraDeDesconto> descontos, Funcionario Funcionario, Month mes, int ano) {
        double adiantamentos = calcularAdiantamentos(descontos, Funcionario, mes, ano);
        double consumoInternoTotal = calcularConsumoInternoTotal(descontos, Funcionario, mes, ano);
        return (consumoInternoTotal + adiantamentos);
    }

    public double calcularSalarioFinal(List<RegraDeDesconto> descontos,
            Funcionario funcionario,
            Month mes,
            int ano) {
        double salarioBase = funcionario.getSalarioBase();
        double totalDescontos = calcularTotalDescontos(descontos, funcionario, mes, ano);
        return salarioBase - totalDescontos;
    }
}