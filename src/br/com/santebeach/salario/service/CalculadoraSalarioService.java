package br.com.santebeach.salario.service;

import java.time.Month;
import java.util.List;

import br.com.santebeach.salario.model.Adiantamento;
import br.com.santebeach.salario.model.ConsumoInterno;
import br.com.santebeach.salario.model.Funcionario;

public class CalculadoraSalarioService {

    public double calcularTotalAdiantamentos(Funcionario funcionario,
                                             List<Adiantamento> adiantamentos,
                                             Month mes,
                                             int ano) {
        return adiantamentos.stream()
                .filter(a -> a.getFuncionario().equals(funcionario))
                .filter(a -> a.getData().getMonth().equals(mes))
                .filter(a -> a.getData().getYear() == ano)
                .mapToDouble(Adiantamento::getValor)
                .sum();
    }

    public double calcularTotalConsumo(Funcionario funcionario,
                                       List<ConsumoInterno> consumos,
                                       Month mes,
                                       int ano) {
        return consumos.stream()
                .filter(c -> c.getFuncionario().equals(funcionario))
                .filter(c -> c.getData().getMonth().equals(mes))
                .filter(c -> c.getData().getYear() == ano)
                .mapToDouble(ConsumoInterno::getValorParaDesconto)
                .sum();
    }

    public double calcularSalarioFinal(Funcionario funcionario,
                                       List<Adiantamento> adiantamentos,
                                       List<ConsumoInterno> consumos,
                                       Month mes,
                                       int ano) {

        double salarioBase = funcionario.getSalarioBase();
        double totalAdiantamentos = calcularTotalAdiantamentos(funcionario, adiantamentos, mes, ano);
        double totalConsumo = calcularTotalConsumo(funcionario, consumos, mes, ano);

        return salarioBase - totalAdiantamentos - totalConsumo;
    }
}