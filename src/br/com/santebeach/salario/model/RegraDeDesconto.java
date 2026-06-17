package br.com.santebeach.salario.model;

import java.time.LocalDate;

public interface RegraDeDesconto {

    Funcionario getFuncionario();

    LocalDate getData();

    double getValorParaDesconto();

    String getCategoria();
    
    boolean isCobrado();
}