package br.com.santebeach.salario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.santebeach.salario.model.Adiantamento;
import br.com.santebeach.salario.model.ConsumoInterno;
import br.com.santebeach.salario.model.Funcionario;
import br.com.santebeach.salario.model.Produto;
import br.com.santebeach.salario.model.RegraDeDesconto;
import br.com.santebeach.salario.service.CalculadoraSalarioService;

public class CalculadoraSalarioServiceTest {

    @Test
    public void deveCalcularSalarioSemAdiantamentoNemConsumo() {
        Funcionario func = new Funcionario("Func 1", 2500.0);
        List<RegraDeDesconto> descontos = new ArrayList<>();
        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        double resultado = calculadora.calcularSalarioFinal(func, descontos, Month.JUNE, 2026);

        assertEquals(2500.0, resultado);
    }

    @Test
    public void deveDescontarAdiantamentosDoSalario() {
        Funcionario func = new Funcionario("Func 1", 2500.0);
        List<RegraDeDesconto> descontos = new ArrayList<>();
        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        descontos.add(new Adiantamento(func, LocalDate.of(2026, 6, 5), 300.0));
        descontos.add(new Adiantamento(func, LocalDate.of(2026, 6, 10), 200.0));

        double resultado = calculadora.calcularSalarioFinal(func, descontos, Month.JUNE, 2026);

        assertEquals(2000.0, resultado);
    }

    @Test
    public void deveDescontarConsumoInternoDoSalario() {
        Funcionario func = new Funcionario("Func 1", 2500.0);
        List<RegraDeDesconto> descontos = new ArrayList<>();
        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        Produto espeto = new Produto("Espeto", 10.0);

        // 2 unidades → custo 20.0 → desconto 20 * 1.03 = 20.6
        descontos.add(new ConsumoInterno(func, espeto, LocalDate.of(2026, 6, 3), 2));

        double resultado = calculadora.calcularSalarioFinal(func, descontos, Month.JUNE, 2026);

        assertEquals(2500.0 - 20.0 * 1.03, resultado);
    }

    @Test
    public void deveDescontarAdiantamentosEConsumoJuntos() {
        Funcionario func = new Funcionario("Func 1", 2500.0);
        List<RegraDeDesconto> descontos = new ArrayList<>();
        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        descontos.add(new Adiantamento(func, LocalDate.of(2026, 6, 5), 300.0));

        Produto refrigerante = new Produto("Refrigerante", 5.0);
        // 3 unidades → custo 15.0 → desconto 15 * 1.03 = 15.45
        descontos.add(new ConsumoInterno(func, refrigerante, LocalDate.of(2026, 6, 3), 3));

        double resultado = calculadora.calcularSalarioFinal(func, descontos, Month.JUNE, 2026);

        assertEquals(2500.0 - 300.0 - 15.0 * 1.03, resultado);
    }
}