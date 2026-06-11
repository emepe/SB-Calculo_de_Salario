package br.com.santebeach.salario;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;



public class Main {

    public static void main(String[] args) {

        Funcionario func1 = new Funcionario("Funcionario 1", 2500.0);
        Funcionario func2 = new Funcionario("Funcionario 2", 2300.0);

        Produto espeto = new Produto("Espeto",6.20);
        Produto refrigerante = new Produto("Refrigerante", 3.50);
        Produto suco = new Produto("Suco Lata",3.50);
        Produto agua = new Produto("Agua sem gas", 1.00);
        Produto aguaGasosa = new Produto("Agua com gas", 1.50);
        Produto gatorade = new Produto("Gatorade", 5.00);
        Produto sorvete = new Produto("Sorvete", 7.90);
        Produto cerveja = new Produto("Cerveja", 6.29);
        Produto xequemate = new Produto("Xeque Mate", 7.50);
        Produto skolbeats = new Produto("Skol Beats", 7.50);
        Produto lambelambe = new Produto("Lambe Lambe", 8.50);
        Produto acaitradicional = new Produto("Açaí Tradicional", 6.99);
        Produto acaigranola = new Produto("Açaí com Granola", 7.99);
        Produto acaileite = new Produto("Açaí com Leite em Pó", 7.99);
        Produto redbull = new Produto("RedBull", 7.92);
        Produto brownie = new Produto("Brownie", 5.27);
        Produto brownieEspecial = new Produto("Brownie Especial", 6.06);
        Produto pacoca = new Produto("Paçoca", 0.70);
        Produto bananinha = new Produto("Bananinha", 1.39);
        Produto batataChips = new Produto("Batata Chips", 5.25);
        Produto paoQueijo = new Produto("Pão de Queijo", 4.50);
        Produto empada = new Produto("Empada", 4.30);

        List<Adiantamento> adiantamentos = new ArrayList<>();
        List<ConsumoInterno> consumos = new ArrayList<>();

        // Exemplo: adiantamentos em junho
        adiantamentos.add(new Adiantamento(func1, LocalDate.of(2026, 6, 5), 0));
        adiantamentos.add(new Adiantamento(func2, LocalDate.of(2026, 6, 10), 0));

        // Exemplo: consumo interno em junho (produtos + quantidade; valores de custo)
        // Funcionario 1: dia 03 -> 2 espetos e 2 refrigerantes
        consumos.add(new ConsumoInterno(func1, espeto, LocalDate.of(2026, 6, 3), 2));
        consumos.add(new ConsumoInterno(func1, refrigerante, LocalDate.of(2026, 6, 3), 2));

        // Funcionario 1: dia 06 -> 1 espetos e 3 sucos
        consumos.add(new ConsumoInterno(func1, espeto, LocalDate.of(2026, 6, 6), 1));
        consumos.add(new ConsumoInterno(func1, suco, LocalDate.of(2026, 6, 6), 2));
        consumos.add(new ConsumoInterno(func1, gatorade, LocalDate.of(2026, 6, 6), 1));
        
        // Funcionario 2: dia 04 -> 2 espetos e 2 sucos
        consumos.add(new ConsumoInterno(func2, espeto, LocalDate.of(2026, 6, 4), 2));
        consumos.add(new ConsumoInterno(func2, suco, LocalDate.of(2026, 6, 4), 2));

        // Funcionario 2: dia 07 -> 2 espetos e 3 skol beats
        consumos.add(new ConsumoInterno(func2, espeto, LocalDate.of(2026, 6, 7), 2));
        consumos.add(new ConsumoInterno(func2, skolbeats, LocalDate.of(2026, 6, 7), 3));


        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        Month mesReferencia = Month.JUNE;
        int anoReferencia = 2026;

        // OUTPUT 
        double totalAdiantamentosFunc1 = calculadora.calcularTotalAdiantamentos(func1, adiantamentos, mesReferencia, anoReferencia);
        double totalConsumoFunc1 = calculadora.calcularTotalConsumo(func1, consumos, mesReferencia, anoReferencia);
        double salarioFinalFunc1 = calculadora.calcularSalarioFinal(func1, adiantamentos, consumos, mesReferencia, anoReferencia);

        double totalAdiantamentosFunc2 = calculadora.calcularTotalAdiantamentos(func2, adiantamentos, mesReferencia, anoReferencia);
        double totalConsumoFunc2 = calculadora.calcularTotalConsumo(func2, consumos, mesReferencia, anoReferencia);
        double salarioFinalFunc2 = calculadora.calcularSalarioFinal(func2, adiantamentos, consumos, mesReferencia, anoReferencia);

        System.out.println("");
        System.out.println("=================================================");
        System.out.println("       RESUMO DE PAGAMENTO - " + mesReferencia + " / " + anoReferencia);
        System.out.println("=================================================");

        imprimirResumoFuncionario(func1, totalAdiantamentosFunc1, totalConsumoFunc1, salarioFinalFunc1);
        System.out.println("");
        imprimirResumoFuncionario(func2, totalAdiantamentosFunc2, totalConsumoFunc2, salarioFinalFunc2);

        System.out.println("=================================================");
        System.out.println("");


    }

    private static void imprimirResumoFuncionario(Funcionario func,
                                                  double totalAdiantamentos,
                                                  double totalConsumo,
                                                  double salarioFinal) {

        System.out.println("Funcionario: " + func.getNome());
        System.out.println("-------------------------------------------------");
        System.out.println(String.format("%-35s R$ %8.2f", " Salario base:", func.getSalarioBase()));
        System.out.println(String.format("%-35s R$ %8.2f", " Total de adiantamentos:", totalAdiantamentos));
        System.out.println(String.format("%-35s R$ %8.2f", " Total de consumo (custo + 3%):", totalConsumo));
        System.out.println(String.format("%-35s R$ %8.2f", " Salario final:", salarioFinal));
    }
}