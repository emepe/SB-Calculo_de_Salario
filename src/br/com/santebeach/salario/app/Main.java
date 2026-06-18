package br.com.santebeach.salario.app;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import br.com.santebeach.salario.model.Adiantamento;
import br.com.santebeach.salario.model.ConsumoInterno;
import br.com.santebeach.salario.model.Funcionario;
import br.com.santebeach.salario.model.Produto;
import br.com.santebeach.salario.model.RegraDeDesconto;
import br.com.santebeach.salario.service.CalculadoraSalarioService;

public class Main {

    public static void main(String[] args) {

        Funcionario func1 = new Funcionario("Raphael", 2500.0);
        Funcionario func2 = new Funcionario("Cauan", 3000.0);

        func1.setDiasASeremTrabalhados(28);
        func2.setDiasASeremTrabalhados(29);

        Produto espeto = new Produto("Espeto", 6.20, 12.00, false);
        Produto refrigerante = new Produto("Refrigerante", 3.50, 6.00, false);
        Produto suco = new Produto("Suco Lata", 3.50, 6.00, false);
        Produto agua = new Produto("Agua sem gas", 1.00, 4.00, false);
        Produto aguaGasosa = new Produto("Agua com gas", 1.50, 5.00, false);
        Produto gatorade = new Produto("Gatorade", 5.00, 10.00, true);
        Produto sorvete = new Produto("Sorvete", 7.90, 13.00, true);
        Produto cerveja = new Produto("Cerveja", 6.29, 12.00, true);
        Produto xequemate = new Produto("Xeque Mate", 7.50, 15.00, true);
        Produto skolbeats = new Produto("Skol Beats", 7.50, 14.00, true);
        Produto lambelambe = new Produto("Lambe Lambe", 8.50, 16.00, true);
        Produto acaitradicional = new Produto("Açaí Tradicional", 6.99, 14.00, true);
        Produto acaigranola = new Produto("Açaí com Granola", 7.99, 14.00, true);
        Produto acaileite = new Produto("Açaí com Leite em Pó", 7.99, 15.00, true);
        Produto redbull = new Produto("RedBull", 7.92, 12.00, true);
        Produto brownie = new Produto("Brownie", 5.27, 10.00, true);
        Produto brownieEspecial = new Produto("Brownie Especial", 6.06, 12.00, true);
        Produto pacoca = new Produto("Paçoca", 0.70, 1.50, true);
        Produto bananinha = new Produto("Bananinha", 1.39, 3.00, true);
        Produto batataChips = new Produto("Batata Chips", 5.25, 10.00, true);
        Produto paoQueijo = new Produto("Pão de Queijo", 4.50, 6.00, true);
        Produto empada = new Produto("Empada", 4.30, 7.00, true);

        // lista única de descontos (adiantamentos + consumos juntos)
        List<RegraDeDesconto> descontos = new ArrayList<>();

        // adiantamentos
        descontos.add(new Adiantamento(func1, LocalDate.of(2026, 6, 5), 1000));
        descontos.add(new Adiantamento(func2, LocalDate.of(2026, 6, 10), 500));

        // consumos func1
        descontos.add(new ConsumoInterno(func1, espeto, LocalDate.of(2026, 6, 3), 84));
        descontos.add(new ConsumoInterno(func1, refrigerante, LocalDate.of(2026, 6, 3), 1));
        descontos.add(new ConsumoInterno(func1, cerveja, LocalDate.of(2026, 6, 6), 0));
        descontos.add(new ConsumoInterno(func1, suco, LocalDate.of(2026, 6, 6), 0));
        descontos.add(new ConsumoInterno(func1, gatorade, LocalDate.of(2026, 6, 6), 0));

        // consumos func2
        descontos.add(new ConsumoInterno(func2, espeto, LocalDate.of(2026, 6, 3), 87));
        descontos.add(new ConsumoInterno(func2, refrigerante, LocalDate.of(2026, 6, 3), 1));
        descontos.add(new ConsumoInterno(func2, cerveja, LocalDate.of(2026, 6, 6), 0));
        descontos.add(new ConsumoInterno(func2, suco, LocalDate.of(2026, 6, 6), 0));
        descontos.add(new ConsumoInterno(func2, gatorade, LocalDate.of(2026, 6, 6), 0));

        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        Month mesReferencia = Month.JUNE;
        int anoReferencia = 2026;


        double adiantamentosFunc1 = calculadora.calcularAdiantamentos(descontos, func1, mesReferencia, anoReferencia);
        double consumacaoFunc1 = calculadora.calcularConsumoInternoTotal(descontos, func1, mesReferencia, anoReferencia);
        double limiteConsumacaoFunc1 = calculadora.limiteConsumoMensal(func1.getDiasASeremTrabalhados());
        double totalDescontosFunc1 = calculadora.calcularTotalDescontos(descontos, func1, mesReferencia, anoReferencia);
        double salarioFinalFunc1 = calculadora.calcularSalarioFinal(descontos, func1, mesReferencia, anoReferencia);
        
        double adiantamentosFunc2 = calculadora.calcularAdiantamentos(descontos, func2, mesReferencia, anoReferencia);
        double consumacaoFunc2 = calculadora.calcularConsumoInternoTotal(descontos, func2, mesReferencia, anoReferencia);
        double limiteConsumacaoFunc2 = calculadora.limiteConsumoMensal(func2.getDiasASeremTrabalhados());
        double totalDescontosFunc2 = calculadora.calcularTotalDescontos(descontos, func2, mesReferencia, anoReferencia);
        double salarioFinalFunc2 = calculadora.calcularSalarioFinal(descontos, func2, mesReferencia, anoReferencia);

        System.out.println("=================================================");
        System.out.println("       RESUMO DE PAGAMENTO - " + mesReferencia + " / " + anoReferencia);
        System.out.println("=================================================");

        imprimirResumo(func1, adiantamentosFunc1, consumacaoFunc1, limiteConsumacaoFunc1, totalDescontosFunc1, salarioFinalFunc1);
        System.out.println("=================================================");
        
        imprimirResumo(func2, adiantamentosFunc2, consumacaoFunc2, limiteConsumacaoFunc2, totalDescontosFunc2, salarioFinalFunc2);

        System.out.println("=================================================");
    }

    private static void imprimirResumo(Funcionario func, 
                                       double totalAdiantamentos,
                                       double totalConsumacao,
                                       double limiteConsumacao,
                                       double totalDescontos,
                                       double salarioFinal) {
        System.out.println("Funcionário: " + func.getNome());
        System.out.println("-------------------------------------------------");
        System.out.println(String.format("%-37s R$ %8.2f", "Salário base:", func.getSalarioBase()));
        System.out.println();
        System.out.println(String.format("%-37s R$ %8.2f", "Descontos por adiantamentos:", totalAdiantamentos));
        System.out.println(String.format("%-37s R$ %8.2f", "Descontos por consumação:", totalConsumacao));
        System.out.println(String.format("%-37s R$ %8.2f", "Limite de consumo para o mês:", limiteConsumacao));
        System.out.println(String.format("%-37s R$ %8.2f", "Total de descontos:", totalDescontos));
        System.out.println();
        System.out.println(String.format("%-37s R$ %8.2f", "Salário final:", salarioFinal));
    }
}   