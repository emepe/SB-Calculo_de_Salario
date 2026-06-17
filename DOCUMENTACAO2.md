# Sistema de Cálculo de Salário para Funcionários de um Beach Tennis

## 1. Informações gerais do projeto

- Unidade Curricular: Modelos, Métodos e Técnicas de Engenharia de Software – UniBH
- Professor: Lucas Goulart Silva
- Semestre: 2026.1
- Título do projeto: Sistema de Cálculo de Salário para Funcionários de um Beach Tennis
- Repositório GitHub: https://github.com/emepe/SB-Calculo_de_Salario

- Integrantes do grupo:
  - Breno Grant – 12319432 – [12319432@ulife.com.br](mailto:12319432@ulife.com.br)
  - João Gabriel Oliveira Tavares – 12314592 – [12314592@ulife.com.br](mailto:12314592@ulife.com.br)
  - Maria Paula Pereira Sousa – 12315841 – [12315841@ulife.com.br](mailto:12315841@ulife.com.br)

---

## 2. Definição do Problema

### 2.1 Contexto

- Tipo de negócio: Estabelecimento de Beach Tennis/Bar;
- Cidade: Belo Horizonte – MG;
- Quantidade de funcionários informais: 2 funcionários fixos;
- Forma de pagamento atual: Salário base mensal ajustado por consumação no bar e adiantamentos, se aplicáveis.

### 2.2 Problema atual

No Santê Beach, o cálculo do salário dos dois funcionários é feito atualmente de forma totalmente manual, com apoio de uma planilha em Excel. Essa planilha possui quatro abas principais. Na primeira aba, há um resumo geral com o valor final a pagar para cada funcionário, incluindo o salário base e todos os descontos aplicados no mês.

A segunda e a terceira aba registram a consumação interna diária de cada funcionário no bar, sendo uma aba para cada pessoa. Sempre que um funcionário consome algum item, como espetinhos, refrigerantes ou doces, esses itens são anotados em outra planilha pelos próprios colaboradores e depois transcritos manualmente pela gestora para a planilha de controle. Ao final do mês, é feito o cálculo de quanto foi consumido acima do limite definido de trinta e seis reais (nos produtos liberados para consumação) ou de itens que não entram como cortesia (por exemplo, bebidas alcoólicas e outros snacks), e esse total é usado como desconto no pagamento.

Na quarta aba, existe uma tabela em formato de calendário, onde são lançados manualmente os dias em que cada funcionário compareceu ao trabalho. A partir dessa visualização, é possível identificar faltas e calcular os descontos correspondentes com base no valor da diária daquele dia.

Todo esse processo depende de lançamentos manuais, cruzamento de informações entre abas e conferência "no olho", o que traz diversas dificuldades na rotina. A cada fechamento de mês, é necessário conferir se todos os dias trabalhados foram marcados corretamente na aba de calendário, se todas as consumações foram transcritas sem erro da planilha que os funcionários preenchem e se todos os adiantamentos foram realmente identificados no extrato bancário.

Como essas informações estão espalhadas em fontes diferentes (planilha de calendário, planilha de consumação, extrato do banco, grupos de WhatsApp, etc), qualquer descuido na digitação de um valor, na escolha de uma célula ou na soma de uma coluna pode gerar erros no cálculo final do salário, sem que isso seja percebido imediatamente. Além disso, o processo é demorado e exige muita atenção, o que aumenta a chance de retrabalho quando é preciso corrigir algum lançamento ou refazer contas. Por fim, a falta de um sistema centralizado dificulta a consulta ao histórico de meses anteriores, tornando mais trabalhoso responder dúvidas dos funcionários sobre pagamentos passados ou comparar períodos diferentes.

### 2.3 Objetivo da solução

Este trabalho propõe o desenvolvimento de um sistema back-end em Java para apoiar o cálculo mensal de salário dos funcionários do Santê Beach, a partir do salário base definido e dos ajustes feitos por adiantamentos e consumação interna registrada ao longo do mês. A solução tem como objetivo automatizar as principais regras de cálculo, reduzir a dependência de planilhas e lançamentos manuais, centralizar as informações em um único sistema e tornar o fechamento mensal dos pagamentos mais rápido, confiável e fácil de conferir.

---

## 3. Escopo do Sistema (Versão 1)

### 3.1 Escopo incluído

O sistema desenvolvido neste trabalho irá:

- Cadastrar funcionários com seus dados básicos (ex.: nome, salário base).
- Registrar adiantamentos de salário para cada funcionário, informando data e valor.
- Registrar consumação interna de cada funcionário no mês (por produto e quantidade total).
- Calcular o total de descontos de um funcionário em um determinado mês, considerando adiantamentos e consumação interna.
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

---

## 4. Levantamento e Análise de Requisitos

### 4.1 Atores

- **Gestora do estabelecimento**: responsável por registrar informações e calcular os salários.
- **Funcionário**: pessoa que recebe o salário calculado (usuário indireto do sistema).

### 4.2 User Stories (abordagem ágil)

- **US01** – Como gestora, quero cadastrar os funcionários com seus salários base para que o sistema consiga calcular seus salários mensais.
- **US02** – Como gestora, quero registrar adiantamentos de salário por funcionário para que esses valores sejam descontados no cálculo do mês.
- **US03** – Como gestora, quero registrar a consumação interna de cada funcionário no mês para que o sistema some esses valores com acréscimo de 3% e desconte do salário.
- **US04** – Como gestora, quero solicitar o cálculo do salário final de um funcionário em um determinado mês para saber quanto devo pagar via Pix.
- **US05** – Como gestora, quero visualizar um resumo dos valores considerados no cálculo (salário base, total de descontos e salário final) para conferir antes de realizar o pagamento.

### 4.3 Requisitos funcionais (RF)

- **RF01**: O sistema deve permitir cadastrar funcionários com nome e salário base.
- **RF02**: O sistema deve permitir registrar adiantamentos com data e valor para um funcionário.
- **RF03**: O sistema deve permitir registrar consumação interna para um funcionário, informando produto e quantidade.
- **RF04**: O sistema deve calcular o total de descontos de um funcionário em um mês, somando adiantamentos e consumação interna.
- **RF05**: O sistema deve calcular o total de consumação interna de um funcionário em um mês, aplicando acréscimo de 3%.
- **RF06**: O sistema deve calcular o salário final do funcionário no mês, considerando salário base e total de descontos.
- **RF07**: O sistema deve disponibilizar um resumo dos valores considerados no cálculo do salário de um funcionário.

### 4.4 Requisitos não funcionais (RNF)
(REVER DEPOIS)
- **RNF01**: O sistema deve realizar o cálculo do salário de um funcionário de forma praticamente instantânea, não ultrapassando alguns milissegundos para um mês de dados em memória.
- **RNF02**: O sistema deve garantir consistência dos cálculos, aplicando as mesmas regras de negócio de forma uniforme para todos os funcionários e meses.
- **RNF03**: O sistema deve ser de fácil manutenção, com código organizado em camadas (domínio e serviços) e classes com responsabilidades bem definidas.
- **RNF04**: O sistema deve permitir a inclusão de novas regras de cálculo (por exemplo, controle de faltas) com impacto mínimo nas classes já existentes.
- **RNF05**: O sistema deve armazenar e processar valores monetários com precisão adequada para evitar erros de arredondamento relevantes nos salários.

---

## 5. Modelagem da Solução

### 5.1 Classes no Diagrama de Classes
(REFAZER)
![Diagrama de Classes](<Diagrama de Classes.drawio.png>)

O sistema de cálculo de salários é composto por seis classes principais: `Funcionario`, `Produto`, `Adiantamento`, `ConsumoInterno`, `RegraDeDesconto` e `CalculadoraSalarioService`.

A classe `Funcionario` representa o colaborador da empresa, armazenando seu nome e o salário base. Ela oferece métodos de acesso para ler e alterar o salário base, sendo a entidade central sobre a qual são calculados adiantamentos, consumos e o salário final.

A classe `Produto` modela os itens disponíveis para consumo interno, contendo o nome do produto e seu preço de custo, além de métodos para acessar essas informações.

A interface `RegraDeDesconto` define o contrato que qualquer tipo de desconto deve seguir, expondo os métodos `getFuncionario()`, `getData()` e `getValorParaDesconto()`. Essa abstração permite que novos tipos de desconto sejam adicionados ao sistema sem necessidade de modificar a lógica de cálculo já existente.

A classe `Adiantamento` registra valores adiantados ao funcionário em uma determinada data. Implementa a interface `RegraDeDesconto`, retornando o próprio valor do adiantamento como desconto.

A classe `ConsumoInterno` registra o consumo de produtos realizado por um funcionário em uma data específica. Ela mantém referências a um `Funcionario` e a um `Produto`, bem como a quantidade consumida e a data. Implementa a interface `RegraDeDesconto`, calculando o valor de desconto com acréscimo de 3% sobre o custo total (`getValorCustoTotal`).

A classe `CalculadoraSalarioService` concentra a lógica de negócio relacionada ao cálculo de valores. Ela recebe uma lista de `RegraDeDesconto` e oferece operações para calcular o total de descontos de um funcionário em um determinado mês e ano e o salário final, subtraindo do salário base o total de descontos calculado.

Do ponto de vista de relacionamentos, um `Funcionario` pode estar associado a vários `Adiantamento` e a vários `ConsumoInterno` (multiplicidade 1 para o funcionário e 0..* para os registros). Cada `ConsumoInterno` está associado a exatamente um `Produto`, enquanto um `Produto` pode aparecer em muitos registros de consumo. Tanto `Adiantamento` quanto `ConsumoInterno` implementam a interface `RegraDeDesconto`. A classe `CalculadoraSalarioService` depende da abstração `RegraDeDesconto`, sem depender diretamente das classes concretas.

### 5.2 Outros diagramas (opcional)
(FAZER DIAGRAMA DE CASO DE USO)

- Diagrama de casos de uso com:
  - Atores: Gestora, Funcionário.
  - Casos de uso principais: Cadastrar Funcionário, Registrar Adiantamento, Registrar Consumo, Calcular Salário.

---

## 6. Desenvolvimento da Solução

### 6.1 Tecnologias utilizadas

O projeto foi desenvolvido em Java, com foco na implementação back-end da regra de cálculo salarial dos funcionários. Para validação do comportamento da aplicação, foram criados testes unitários utilizando JUnit. O desenvolvimento e a organização do repositório foram realizados com apoio de ferramentas de versionamento e ambiente de desenvolvimento.

- Linguagem: Java (versão 17.0.19).
- Frameworks/bibliotecas: JUnit 5 para testes unitários.
- Ferramentas: IDE VS Code, Git, GitHub.

### 6.2 Organização do código

O projeto foi organizado em diretórios separados para arquivos compilados, bibliotecas externas, código-fonte, testes e documentação. Dentro da pasta `src`, as classes foram distribuídas em pacotes Java de acordo com sua responsabilidade, separando a aplicação principal, as entidades de domínio e a camada de serviço. Os testes unitários foram mantidos em uma estrutura paralela na pasta `test`, enquanto a pasta `bin` armazena os arquivos compilados e a pasta `lib` reúne as bibliotecas utilizadas no projeto.

(CORRIGIR)

```text
SB-CALCULO_DE_SALARIO/
├── bin/
├── diagrams/
│   ├── Diagrama de Classes.drawio
│   └── Diagrama de Classes.png
├── lib/
│   ├── junit-jupiter-api-5.10.2.jar
│   ├── junit-jupiter-engine-5.10.2.jar
│   ├── junit-platform-console-standalone-1.10.2.jar
│   └── mockito-core-5.8.0.jar
├── src/
│   └── br/
│       └── com/
│           └── santebeach/
│               └── salario/
│                   ├── app/
│                   │   └── Main.java
│                   ├── model/
│                   │   ├── Adiantamento.java
│                   │   ├── ConsumoInterno.java
│                   │   ├── Funcionario.java
│                   │   ├── Produto.java
│                   │   └── RegraDeDesconto.java
│                   └── service/
│                       └── CalculadoraSalarioService.java
├── test/
│   └── br/
│       └── com/
│           └── santebeach/
│               └── salario/
│                   └── service/
│                       └── CalculadoraSalarioServiceTest.java
└── DOCUMENTACAO.md
```

### 6.3 Princípios SOLID e padrões de projeto

#### SRP – Single Responsibility Principle

Cada classe tem uma responsabilidade bem definida:

- `Funcionario`: representa o colaborador, armazenando nome e salário base.
- `Produto`: representa um item consumível, com nome e preço de custo.
- `Adiantamento`: representa um adiantamento financeiro feito a um funcionário em uma data específica, implementando a regra de desconto correspondente.
- `ConsumoInterno`: representa um registro de consumo de produtos por cada funcionário, calculando o custo total e o valor para desconto com acréscimo de 3%.
- `RegraDeDesconto`: define o contrato de qualquer desconto aplicável ao salário, sem carregar lógica de cálculo própria.
- `CalculadoraSalarioService`: concentra exclusivamente a lógica de cálculo do salário final, somando os descontos e subtraindo do salário base.

#### OCP – Open/Closed Principle

O sistema está aberto para extensão e fechado para modificação. A interface `RegraDeDesconto` permite que novos tipos de desconto (por exemplo, desconto por falta ou desconto por uniforme) sejam adicionados criando uma nova classe que implemente essa interface, sem necessidade de alterar a `CalculadoraSalarioService` ou qualquer outra classe existente.

#### LSP – Liskov Substitution Principle

As classes `Adiantamento` e `ConsumoInterno` implementam a interface `RegraDeDesconto` e podem ser usadas em qualquer lugar onde um `RegraDeDesconto` é esperado, sem quebrar o comportamento do sistema. A `CalculadoraSalarioService` trabalha com uma lista de `RegraDeDesconto` e opera corretamente independentemente de quais implementações concretas estejam presentes nessa lista.

#### ISP – Interface Segregation Principle

A interface `RegraDeDesconto` é pequena e específica, contendo apenas os três métodos que qualquer desconto precisa expor: `getFuncionario()`, `getData()` e `getValorParaDesconto()`. Nenhuma classe é forçada a implementar métodos que não fazem sentido para ela.

#### DIP – Dependency Inversion Principle

A classe `CalculadoraSalarioService`, que representa a camada de alto nível do sistema, não depende das classes concretas `Adiantamento` ou `ConsumoInterno`. Ela depende exclusivamente da abstração `RegraDeDesconto`, invertendo a dependência e tornando o sistema mais flexível e desacoplado.