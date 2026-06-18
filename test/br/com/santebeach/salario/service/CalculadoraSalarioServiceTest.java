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

        double resultado = calculadora.calcularSalarioFinal(descontos, func, Month.JUNE, 2026);

        assertEquals(2500.0, resultado);
    }

    @Test
    public void deveDescontarAdiantamentosDoSalario() {
        Funcionario func = new Funcionario("Func 1", 2500.0);
        List<RegraDeDesconto> descontos = new ArrayList<>();
        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        descontos.add(new Adiantamento(func, LocalDate.of(2026, 6, 5), 300.0));
        descontos.add(new Adiantamento(func, LocalDate.of(2026, 6, 10), 200.0));

        double resultado = calculadora.calcularSalarioFinal(descontos, func, Month.JUNE, 2026);

        assertEquals(2000.0, resultado);
    }

    @Test
    public void deveContarComoCortesiaOConsumoInternoDoSalario() {
        Funcionario func = new Funcionario("Func 1", 2500.00);
        List<RegraDeDesconto> descontos = new ArrayList<>();
        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        Produto espeto = new Produto("Espeto", 6.20, 12.00, false);

        descontos.add(new ConsumoInterno(func, espeto, LocalDate.of(2026, 6, 3), 2));

        double resultado = calculadora.calcularSalarioFinal(descontos, func, Month.JUNE, 2026);

        assertEquals(2500.00, resultado);
    }

    @Test
    public void deveDescontarAdiantamentosEConsumoJuntos() {
        Funcionario func = new Funcionario("Func 1", 2500.0);
        List<RegraDeDesconto> descontos = new ArrayList<>();
        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        descontos.add(new Adiantamento(func, LocalDate.of(2026, 6, 5), 300.0));

        Produto refrigerante = new Produto("Refrigerante", 3.50, 6.00, false);

        descontos.add(new ConsumoInterno(func, refrigerante, LocalDate.of(2026, 6, 3), 3));

        double resultado = calculadora.calcularSalarioFinal(descontos, func, Month.JUNE, 2026);

        assertEquals(2500.0 - 300.0, resultado);
    }

    @Test
    public void deveDescontarConsumoExcedido() {
        Funcionario func = new Funcionario("Func 1", 2500.0);
        List<RegraDeDesconto> descontos = new ArrayList<>();
        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        Produto refrigerante = new Produto("Refrigerante", 3.50, 6.00, false);

        descontos.add(new ConsumoInterno(func, refrigerante, LocalDate.of(2026, 6, 3), 169));

        double resultado = calculadora.calcularSalarioFinal(descontos, func, Month.JUNE, 2026);

        assertEquals(2500.0 - (3.50 * 1.03), resultado);
    }

     @Test
    public void deveDescontarAdiantamentosEConsumoExcedidoFalse() {
        Funcionario func = new Funcionario("Func 1", 2500.0);
        List<RegraDeDesconto> descontos = new ArrayList<>();
        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        descontos.add(new Adiantamento(func, LocalDate.of(2026, 6, 5), 300.0));

        Produto refrigerante = new Produto("Refrigerante", 3.50, 6.00, false);

        descontos.add(new ConsumoInterno(func, refrigerante, LocalDate.of(2026, 6, 3), 169));

        double resultado = calculadora.calcularSalarioFinal(descontos, func, Month.JUNE, 2026);

        assertEquals(2500.0 - 300.0 - (3.50 * 1.03), resultado);
    }

    @Test
    public void deveDescontarAdiantamentosEConsumoExcedidoTrue() {
        Funcionario func = new Funcionario("Func 1", 2500.0);
        List<RegraDeDesconto> descontos = new ArrayList<>();
        CalculadoraSalarioService calculadora = new CalculadoraSalarioService();

        descontos.add(new Adiantamento(func, LocalDate.of(2026, 6, 5), 300.0));

        Produto cerveja = new Produto("Cerveja", 6.29, 12.00, true);

        descontos.add(new ConsumoInterno(func, cerveja, LocalDate.of(2026, 6, 3), 1));

        double resultado = calculadora.calcularSalarioFinal(descontos, func, Month.JUNE, 2026);

        assertEquals(2500.0 - 300.0 - (6.29 * 1.03), resultado);
    }
}
