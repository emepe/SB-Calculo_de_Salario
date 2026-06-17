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


public class Teste {
    public static void main(String[] args) {
        Funcionario Raphael = new Funcionario("Raphael Azevedo Maia", 2000.00);
        Adiantamento adiantamento1 = new Adiantamento(Raphael, LocalDate.of(2024, Month.JANUARY, 10), 500.00);
    }
}