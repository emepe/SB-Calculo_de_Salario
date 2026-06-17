package br.com.santebeach.salario.model;

import java.time.LocalDate;

public interface RegraDeDesconto {

    Funcionario getFuncionario();

    LocalDate getData();

    String getCategoria();
    
    double getPrecoVenda();

    double getPrecoCusto();
    
    boolean isCobrado();

    int getQuantidade();

    double getValorParaDesconto();
}