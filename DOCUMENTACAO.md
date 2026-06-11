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
- Registrar consumação interna de cada funcionário no mês (por produto e quantidade total).
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

## 4. Levantamento e Análise de Requisitos

### 4.1 Atores

- **Gestora do estabelecimento**: responsável por registrar informações e calcular os salários.
- **Funcionário**: pessoa que recebe o salário calculado (usuário indireto do sistema).

### 4.2 User Stories (abordagem ágil)

--------------> (Preencher/adaptar/revisar)

- **US01** – Como gestora, quero cadastrar os funcionários com seus salários base para que o sistema consiga calcular seus salários mensais.
- **US02** – Como gestora, quero registrar adiantamentos de salário por funcionário para que esses valores sejam descontados no cálculo do mês.
- **US03** – Como gestora, quero registrar a consumação interna de cada funcionário no mês para que o sistema some esses valores com acréscimo de 3% e desconte do salário.
- **US04** – Como gestora, quero solicitar o cálculo do salário final de um funcionário em um determinado mês para saber quanto devo pagar via Pix.
- **US05** – Como gestora, quero visualizar um resumo dos valores considerados no cálculo (salário base, total de adiantamentos, total de consumação) para conferir antes de realizar o pagamento.


### 4.3 Requisitos funcionais (RF)

- **RF01**: O sistema deve permitir cadastrar funcionários com nome e salário base.
- **RF02**: O sistema deve permitir registrar adiantamentos com data e valor para um funcionário.
- **RF03**: O sistema deve permitir registrar consumação interna para um funcionário, informando valor total ou produto e quantidade.
- **RF04**: O sistema deve calcular o total de adiantamentos de um funcionário em um mês.
- **RF05**: O sistema deve calcular o total de consumação interna de um funcionário em um mês, aplicando acréscimo de 3%.
- **RF06**: O sistema deve calcular o salário final do funcionário no mês, considerando salário base, adiantamentos e consumação interna.
- **RF07**: O sistema deve disponibilizar um método para retornar um resumo dos valores considerados no cálculo do salário de um funcionário.

### 4.4 Requisitos não funcionais (RNF)

- **RNF01**: O sistema deve realizar o cálculo do salário de um funcionário de forma praticamente instantânea, não ultrapassando alguns milissegundos para um mês de dados em memória.
- **RNF02**: O sistema deve garantir consistência dos cálculos, aplicando as mesmas regras de negócio de forma uniforme para todos os funcionários e meses.
- **RNF03**: O sistema deve ser de fácil manutenção, com código organizado em camadas (domínio e serviços) e classes com responsabilidades bem definidas.
- **RNF04**: O sistema deve permitir a inclusão de novas regras de cálculo (por exemplo, controle de faltas) com impacto mínimo nas classes já existentes.
- **RNF05**: O sistema deve armazenar e processar valores monetários com precisão adequada para evitar erros de arredondamento relevantes nos salários.

## 5. Modelagem da Solução

### 5.1 Classes previstas

--------------> (Preencher/adaptar)

- **Funcionario**
  - atributos sugeridos: id, nome, salarioBase
- **Adiantamento**
  - atributos sugeridos: id, funcionario, data, valor
- **Produto** (opcional, se usar consumação por produto)
  - atributos sugeridos: id, nome, precoCusto
- **ConsumoProduto** ou **ConsumoInterno**
  - atributos sugeridos: id, funcionario, produto (ou descrição), quantidade, valorCustoTotal
- **CalculadoraSalarioService**
  - métodos sugeridos: 
    - calcularTotalAdiantamentos(funcionario, mes)
    - calcularTotalConsumo(funcionario, mes)
    - calcularSalarioFinal(funcionario, mes)

(Depois, desenhar o diagrama de classes em uma ferramenta como draw.io e salvar a imagem.)

### 5.2 Outros diagramas (opcional)

--------------> (Preencher/adaptar/revisar)

- Diagrama de casos de uso com:
  - Atores: Gestora, Funcionário.
  - Casos de uso principais: Cadastrar Funcionário, Registrar Adiantamento, Registrar Consumo, Calcular Salário.

## 6. Desenvolvimento da Solução

### 6.1 Tecnologias utilizadas

--------------> (Preencher/adaptar)

(Preencher)
- Linguagem: Java (versão X).
- Frameworks/bibliotecas: JUnit 5 (e outras, se usadas).
- Ferramentas: IDE (VS Code / IntelliJ / Eclipse), Git, GitHub.

### 6.2 Organização do código

(Exemplo de estrutura)

```text
src/
  main/
    java/
      dominio/
        Funcionario.java
        Adiantamento.java
        Produto.java
        ConsumoInterno.java
      servico/
        CalculadoraSalarioService.java
  test/
    java/
      servico/
        CalculadoraSalarioServiceTest.java
```

(NADA A PARTIR DAQUI PRA BAIXO FOI REVISADO)
 
### 6.3 Princípios SOLID e padrões de projeto

(Preencher conforme sua implementação)

- SRP (Single Responsibility): cada classe de domínio representa um conceito específico (Funcionario, Adiantamento, etc.), e a lógica de cálculo fica concentrada em uma classe de serviço.
- (Opcional) Padrões de projeto utilizados (ex.: Strategy para diferentes formas de cálculo, Factory para criar serviços, etc.) e breve justificativa.


## 7. Testes

(Preencher depois de implementar)

- Descrição das classes testadas (ex.: CalculadoraSalarioService).
- Cenários contemplados:
  - cálculo sem adiantamentos nem consumação;
  - cálculo com adiantamentos;
  - cálculo com consumação;
  - cálculo com adiantamentos e consumação ao mesmo tempo.
- Como executar os testes (comando ou procedimento na IDE).


## 8. Instruções de Execução

(Adaptar conforme seu projeto)

1. Clonar o repositório:
   ```bash
   git clone https://github.com/SEU_USUARIO/beach-tennis-salary-backend.git
   ```
2. Abrir o projeto na IDE de preferência.
3. Compilar o projeto.
4. Executar:
   - a classe `Main` (se existir uma interface simples em console), ou
   - os testes unitários da classe `CalculadoraSalarioServiceTest`.

(Adicionar detalhes se usar Maven/Gradle, por exemplo `mvn test`.)

## 9. Trabalhos futuros

(Preencher com ideias de evolução)

- Incluir controle de faltas e diárias por dia, com valores diferentes para dias de semana e fim de semana.
- Registrar ponto diário (entrada/saída) para cada funcionário.
- Adicionar uma interface web ou mobile para uso direto pelas gestoras.
- Gerar relatórios mais detalhados para os funcionários (histórico de meses, gráficos, etc.).
