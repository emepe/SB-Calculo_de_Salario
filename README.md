# Sistema de Cálculo de Salário para Funcionários de um Beach Tennis

## 1. Informações gerais do projeto

- Unidade Curricular: Modelos, Métodos e Técnicas de Engenharia de Software – UniBH
- Professor: Lucas Goulart Silva
- Semestre: 2026.1
- Título do projeto: Sistema de Cálculo de Salário para Funcionários de um Beach Tennis
- Repositório GitHub: https://github.com/emepe/SB-Calculo_de_Salario

- Integrantes do grupo:
  - Breno Grant – 12319432 – 12319432@ulife.com.br
  - João Gabriel Oliveira Tavares – 12314592 – 12314592@ulife.com.br
  - Maria Paula Pereira Sousa – 12315841 – 12315841@ulife.com.br


## 2. Definição do Problema

### 2.1 Contexto

- Tipo de negócio: Estabelecimento de Beach Tennis/Bar;
- Cidade: Belo Horizonte – MG;
- Quantidade de funcionários informais: 2 funcionários fixos;
- Forma de pagamento atual: Salário base mensal ajustado por consumação no bar e adiantamentos, se aplicáveis.

### 2.2 Problema atual

No Santê Beach, o cálculo do salário dos dois funcionários é feito atualmente de forma totalmente manual, com apoio de uma planilha em Excel. Essa planilha possui quatro abas principais. Na primeira aba, há um resumo geral com o valor final a pagar para cada funcionário, incluindo o salário base e todos os descontos aplicados no mês.

A segunda e a terceira aba registram a consumação interna diária de cada funcionário no bar, sendo uma aba para cada pessoa. Sempre que um funcionário consome algum item, como espetinhos, refrigerantes ou doces, esses itens são anotados em outra planilha pelos próprios colaboradores e depois transcritos manualmente pela gestora para a planilha de controle. Ao final do mês, é feito o cálculo de quanto foi consumido acima de um limite definido ou de itens que não entram como cortesia (por exemplo, bebidas alcoólicas e alguns snacks), e esse total é usado como desconto no pagamento.

Na quarta aba, existe uma tabela em formato de calendário, onde são lançados manualmente os dias em que cada funcionário compareceu ao trabalho. A partir dessa visualização, é possível identificar faltas e calcular os descontos correspondentes com base no valor da diária daquele dia. 

Todo esse processo depende de lançamentos manuais, cruzamento de informações entre abas e conferência “no olho”, o que traz diversas dificuldades na rotina. A cada fechamento de mês, é necessário conferir se todos os dias trabalhados foram marcados corretamente na aba de calendário, se todas as consumações foram transcritas sem erro da planilha que os funcionários preenchem e se todos os adiantamentos foram realmente identificados no extrato bancário.

Como essas informações estão espalhadas em fontes diferentes (planilha de calendário, planilha de consumação, extrato do banco, grupos de WhatsApp, etc), qualquer descuido na digitação de um valor, na escolha de uma célula ou na soma de uma coluna pode gerar erros no cálculo final do salário, sem que isso seja percebido imediatamente. Além disso, o processo é demorado e exige muita atenção, o que aumenta a chance de retrabalho quando é preciso corrigir algum lançamento ou refazer contas. Por fim, a falta de um sistema centralizado dificulta a consulta ao histórico de meses anteriores, tornando mais trabalhoso responder dúvidas dos funcionários sobre pagamentos passados ou comparar períodos diferentes.

### 2.3 Objetivo da solução

Este trabalho propõe o desenvolvimento de um sistema back-end em Java para apoiar o cálculo mensal de salário dos funcionários do Santê Beach, a partir do salário base definido e dos ajustes feitos por adiantamentos e consumação interna registrada ao longo do mês. A solução tem como objetivo automatizar as principais regras de cálculo, reduzir a dependência de planilhas e lançamentos manuais, centralizar as informações em um único sistema e tornar o fechamento mensal dos pagamentos mais rápido, confiável e fácil de conferir.

## 3. Escopo do Sistema (Versão 1)

### 3.1 Escopo incluído

O sistema desenvolvido neste trabalho irá:

- Cadastrar funcionários com seus dados básicos (ex.: nome, salário base).
- Registrar adiantamentos de salário para cada funcionário, informando data e valor.
- Registrar consumação interna de cada funcionário no mês (valor total ou por produto e quantidade, conforme modelagem escolhida).
- Calcular o total de adiantamentos de um funcionário em um determinado mês.
- Calcular o total de consumação interna de um funcionário em um determinado mês, aplicando acréscimo de 3% sobre o valor de custo.
- Calcular o salário final do funcionário no mês, considerando:
  - salário base;
  - total de adiantamentos;
  - total de consumação interna (com acréscimo de 3%).

### 3.2 Fora do escopo (por enquanto)

Não serão implementados nesta versão:

- Controle detalhado de faltas e diárias por dia de trabalho.
- Integração com sistemas bancários ou leitura automática de extrato.
- Interface gráfica (web, desktop ou mobile).
- Controle de estoque completo de produtos do bar.


(FALTA REVISAR ↓)

## 4. Levantamento e Análise de Requisitos

### 4.1 Atores

- **Gestora do estabelecimento**: responsável por registrar informações e calcular os salários.
- **Funcionário**: pessoa que recebe o salário calculado (usuário indireto do sistema).

### 4.2 User Stories (abordagem ágil)

(Exemplos para adaptar)

- **US01** – Como gestora, quero cadastrar os funcionários com seus salários base para que o sistema consiga calcular seus salários mensais.
- **US02** – Como gestora, quero registrar adiantamentos de salário por funcionário para que esses valores sejam descontados no cálculo do mês.
- **US03** – Como gestora, quero registrar a consumação interna de cada funcionário no mês para que o sistema some esses valores com acréscimo de 3% e desconte do salário.
- **US04** – Como gestora, quero solicitar o cálculo do salário final de um funcionário em um determinado mês para saber quanto devo pagar via Pix.
- **US05** – Como gestora, quero visualizar um resumo dos valores considerados no cálculo (salário base, total de adiantamentos, total de consumação) para conferir antes de realizar o pagamento.

(Caso queira, você pode adicionar critérios de aceitação embaixo de cada US.)

### 4.3 Requisitos funcionais (RF)

- **RF01**: O sistema deve permitir cadastrar funcionários com nome e salário base.
- **RF02**: O sistema deve permitir registrar adiantamentos com data e valor para um funcionário.
- **RF03**: O sistema deve permitir registrar consumação interna para um funcionário, informando valor total ou produto e quantidade.
- **RF04**: O sistema deve calcular o total de adiantamentos de um funcionário em um mês.
- **RF05**: O sistema deve calcular o total de consumação interna de um funcionário em um mês, aplicando acréscimo de 3%.
- **RF06**: O sistema deve calcular o salário final do funcionário no mês, considerando salário base, adiantamentos e consumação interna.
- **RF07**: O sistema deve disponibilizar um método para retornar um resumo dos valores considerados no cálculo do salário de um funcionário.

### 4.4 Requisitos não funcionais (RNF)

- **RNF01**: O sistema deve ser implementado utilizando a linguagem Java.
- **RNF02**: O sistema deve possuir testes unitários para as principais regras de cálculo de salário.
- **RNF03**: O sistema deve seguir princípios de orientação a objetos e SOLID.
- **RNF04**: O código-fonte deve ser versionado em um repositório Git hospedado no GitHub.
- **RNF05**: O projeto deve estar organizado de forma clara, com separação entre código de domínio, serviços e testes.


(...)
