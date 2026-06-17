package br.com.santebeach.salario.service;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import br.com.santebeach.salario.model.Funcionario;
import br.com.santebeach.salario.model.RegraDeDesconto;

public class CalculadoraSalarioService {

    public double limiteConsumoMensal(Month mes, int ano) {
        double consumoDiario = 36.00;
        List<Month> mesesCom31Dias = Arrays.asList(
                Month.JANUARY,
                Month.MARCH,
                Month.MAY,
                Month.JULY,
                Month.AUGUST,
                Month.OCTOBER,
                Month.DECEMBER
        );
        List<Month> mesesCom30Dias = Arrays.asList(
                Month.APRIL,
                Month.JUNE,
                Month.SEPTEMBER,
                Month.NOVEMBER
        );

        if (mesesCom31Dias.contains(mes)) {
            return consumoDiario * 31;
        } else if (mesesCom30Dias.contains(mes)) {
            return consumoDiario * 30;
        } else if (mes == Month.FEBRUARY) {
            if(ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) {
                return consumoDiario * 29; // Ano bissexto
            }
            return consumoDiario * 28; // Considerando ano não bissexto
        } else {
            throw new IllegalArgumentException("Mês inválido: " + mes);
        }
    }

    private double calcularAdiantamentos(List<RegraDeDesconto> descontos,Funcionario Funcionario, Month mes, int ano) {
        return descontos.stream()
                .filter(d -> d.getData().getMonth() == mes && d.getData().getYear() == ano && d.getCategoria() == "Adiantamento" && d.getFuncionario().equals(Funcionario))
                .mapToDouble(RegraDeDesconto::getValorParaDesconto)
                .sum();
    }

    private double calcularConsumoInternoCobraveis(List<RegraDeDesconto> descontos,Funcionario Funcionario, Month mes, int ano) {
        return descontos.stream()
                .filter(d -> d.getData().getMonth() == mes && d.getData().getYear() == ano && d.getCategoria() == "Consumo" && d.getFuncionario().equals(Funcionario) && d.isCobrado() == true)
                .mapToDouble(RegraDeDesconto::getValorParaDesconto)
                .sum();
    }

    private double calcularConsumoInternoNotCobraveis(List<RegraDeDesconto> descontos,Funcionario Funcionario, Month mes, int ano) {
        double ValorTotal = 0.0;
        double limiteConsumo = limiteConsumoMensal(mes, ano);
        for (RegraDeDesconto desconto : descontos) {
            if (desconto.getData().getMonth() == mes && desconto.getData().getYear() == ano && desconto.getCategoria() == "Consumo" && desconto.getFuncionario().equals(Funcionario) && desconto.isCobrado() == false) {
                if (valorTotal < limiteConsumo) {
                    ValorTotal += desconto.getValorParaDesconto();
                }
            }
        }
        
        return retorno;
    }

    private double calcularTotalDescontos(List<RegraDeDesconto> descontos,Funcionario Funcionario, Month mes, int ano) {
        double adiantamentos = calcularAdiantamentos(descontos, Funcionario, mes, ano);
        double consumoInternoCobraveis = calcularConsumoInternoCobraveis(descontos, Funcionario, mes, ano);
        double consumoInternoNotCobraveis = calcularConsumoInternoNotCobraveis(descontos, Funcionario, mes, ano);
        double limiteConsumo = limiteConsumoMensal(mes, ano);
        return adiantamentos + consumoInternoCobraveis + consumoInternoNotCobraveis;
    }
    

    public double calcularSalarioFinal(Funcionario funcionario,
                                       List<RegraDeDesconto> descontos,
                                       Month mes,
                                       int ano) {
        double salarioBase = funcionario.getSalarioBase();
        double totalDescontos = calcularTotalDescontos(funcionario, descontos, mes, ano);
        return salarioBase - totalDescontos;
    }
}