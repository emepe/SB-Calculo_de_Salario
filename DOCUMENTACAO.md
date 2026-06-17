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

### 5.1 Classes no Diagrama de Classes

![alt text](<Diagrama de Classes.drawio.png>)

O sistema de cálculo de salários é composto por cinco classes principais: ``Funcionario``, ``Produto``, ``Adiantamento``, ``ConsumoInterno`` e ``CalculadoraSalarioService``.

A classe ``Funcionario`` representa o colaborador da empresa, armazenando seu nome e o salário base. Ela oferece métodos de acesso para ler e alterar o salário base, sendo a entidade central sobre a qual são calculados adiantamentos, consumos e o salário final.

A classe ``Produto`` modela os itens disponíveis para consumo interno, contendo o nome do produto e seu preço de custo, além de métodos para acessar essas informações.

A classe ``Adiantamento`` registra valores adiantados ao funcionário em uma determinada data. Cada objeto dessa classe mantém uma associação a um único ``Funcionario``, a data do adiantamento e o valor correspondente, além de métodos para consulta desses dados.

A classe ``ConsumoInterno`` registra o consumo de produtos realizado por um funcionário em uma data específica. Ela mantém referências a um ``Funcionario`` e a um ``Produto``, bem como a quantidade consumida e a data. A partir dessas informações, disponibiliza operações para calcular o valor total de custo do consumo (``getValorCustoTotal``) e o valor que será descontado do salário, aplicando um acréscimo de 3% sobre o custo (``getValorParaDesconto``).

A classe ``CalculadoraSalarioService`` concentra a lógica de negócio relacionada ao cálculo de valores. Ela oferece operações para calcular o total de adiantamentos de um funcionário em um determinado mês e ano, o total de consumo interno no mesmo período e, por fim, o salário final, subtraindo do salário base o total de adiantamentos e o total de consumo para desconto.

Do ponto de vista de relacionamentos, um ``Funcionario`` pode estar associado a vários ``Adiantamento`` e a vários ``ConsumoInterno`` (multiplicidade 1 para o funcionário e 0..* para os registros). Cada ``ConsumoInterno`` está associado a exatamente um ``Produto``, enquanto um ``Produto`` pode aparecer em muitos registros de consumo (1 para o produto e 0..* para ``ConsumoInterno``). A classe ``CalculadoraSalarioService`` depende de ``Funcionario``, ``Adiantamento`` e ``ConsumoInterno``, utilizando essas classes como parâmetros em seus métodos para realizar os cálculos, mas sem mantê-las como atributos internos.



### 5.2 **Outros** diagramas (opcional)

--------------> (Preencher/adaptar/revisar)

- Diagrama de casos de uso com:
  - Atores: Gestora, Funcionário.
  - Casos de uso principais: Cadastrar Funcionário, Registrar Adiantamento, Registrar Consumo, Calcular Salário.

## 6. Desenvolvimento da Solução

### 6.1 Tecnologias utilizadas

O projeto foi desenvolvido em Java, com foco na implementação back-end da regra de cálculo salarial dos funcionários. Para validação do comportamento da aplicação, foram criados testes unitários utilizando JUnit. O desenvolvimento e a organização do repositório foram realizados com apoio de ferramentas de versionamento e ambiente de desenvolvimento.

- Linguagem: Java (versão 17.0.19).
- Frameworks/bibliotecas: JUnit 5 para testes unitários.
- Ferramentas: IDE VS Code, Git, GitHub.

### 6.2 Organização do código

O projeto foi organizado em diretórios separados para arquivos compilados, bibliotecas externas, código-fonte, testes e documentação. Dentro da pasta `src`, as classes foram distribuídas em pacotes Java de acordo com sua responsabilidade, separando a aplicação principal, as entidades de domínio e a camada de serviço. Os testes unitários foram mantidos em uma estrutura paralela na pasta `test`, enquanto a pasta `bin` armazena os arquivos compilados e a pasta `lib` reúne as bibliotecas utilizadas no projeto.

```text
SB-CALCULO_DE_SALARIO/
├── bin/
│   └── br/
│       └── com/
│           └── santebeach/
│               └── salario/
│                   ├── Adiantamento.class
│                   ├── CalculadoraSalarioService.class
│                   ├── CalculadoraSalarioServiceTest.class
│                   ├── ConsumoInterno.class
│                   ├── Funcionario.class
│                   ├── Main.class
│                   └── Produto.class
├── diagrams/
│   ├── Diagrama de Classes.drawio
│   └── Diagrama de Classes.png
├── lib/
│   ├── byte-buddy-1.14.0.jar
│   ├── byte-buddy-agent-1.14.0.jar
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
│                   │   └── Produto.java
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

(NADA A PARTIR DAQUI PRA BAIXO FOI REVISADO)
 
### 6.3 Princípios SOLID e padrões de projeto

**SRP (Single Responsibility Principle)**  
No projeto atual, cada classe tem uma responsabilidade bem definida:

- `Funcionario`: representa o colaborador, armazenando nome e salário base.  
- `Produto`: representa um item consumível, com nome e preço de custo.  
- `Adiantamento`: representa um adiantamento financeiro feito a um funcionário em uma data específica.  
- `ConsumoInterno`: representa um registro de consumo de um produto por um funcionário, em certa data e quantidade, além de calcular o custo total e o valor para desconto.  
- `CalculadoraSalarioService`: concentra a lógica de cálculo de valores, incluindo o total de adiantamentos, o total de consumo interno e o salário final de um funcionário em um determinado mês e ano.

Assim, a lógica de negócio de cálculo fica centralizada em `CalculadoraSalarioService`, enquanto as classes de modelo focam em representar dados e comportamentos diretamente relacionados ao próprio objeto.

**Outros princípios/padrões**

Até o estado atual do projeto não há uso explícito de padrões como Strategy, Factory ou similares.  
A arquitetura segue uma separação simples entre:

- camada de modelo (`Funcionario`, `Produto`, `Adiantamento`, `ConsumoInterno`), e  
- uma classe de serviço (`CalculadoraSalarioService`) utilizada pela classe `Main` para orquestrar a execução.

---

## 7. Testes

Nesta etapa, foram considerados testes voltados principalmente para a classe `CalculadoraSalarioService`, por concentrar a lógica de cálculo do sistema, e para a classe `ConsumoInterno`, por conter operações de cálculo diretamente relacionadas ao desconto aplicado ao salário.[1]

Os cenários de teste contemplam o comportamento esperado do sistema em situações essenciais do domínio do problema:

- cálculo sem adiantamentos nem consumação, em que o salário final deve permanecer igual ao salário base;
- cálculo com adiantamentos registrados no mês e ano de referência;
- cálculo com consumação interna registrada no período, considerando o custo total do produto e o acréscimo de 3% para desconto;
- cálculo com adiantamentos e consumação ao mesmo tempo, verificando a subtração correta de ambos os totais no salário final.[1]

Os testes também devem considerar a filtragem correta por funcionário, mês e ano, garantindo que registros de outros períodos ou de outros funcionários não interfiram nos resultados do cálculo salarial.[1]

Quando implementados em uma classe de teste como `CalculadoraSalarioServiceTest`, esses testes podem ser executados diretamente pela IDE utilizada no projeto. Caso o projeto seja configurado futuramente com ferramentas de automação como Maven ou Gradle, os comandos de execução poderão ser documentados de forma específica no repositório.[1]

## 8. Instruções de Execução

O projeto está estruturado em pacotes Java organizados por domínio (`model`), serviço (`service`) e aplicação (`app`), com uma classe principal responsável por demonstrar a execução do sistema em console.[1]

Para executar a aplicação, recomenda-se o seguinte procedimento:

1. Clonar o repositório do projeto:
   ```bash
   git clone https://github.com/emepe/SB-Calculo_de_Salario.git
   ```

2. Abrir o projeto em uma IDE Java de preferência, como VS Code, IntelliJ IDEA ou Eclipse.

3. Garantir que a estrutura de diretórios e pacotes esteja preservada, conforme o código-fonte disponível no repositório.[1]

4. Compilar o projeto.

5. Executar a classe principal localizada em:
   ```
   src/br/com/santebeach/salario/app/Main.java
   ```

Ao executar a aplicação, o sistema imprime no console um resumo de pagamento dos funcionários cadastrados, exibindo o salário base, o total de adiantamentos, o total de consumo interno com acréscimo de 3% e o salário final calculado para o mês e ano definidos na aplicação.[1]

Caso existam testes unitários implementados no projeto, eles também poderão ser executados diretamente pela IDE por meio da classe de testes correspondente.[1]

## 9. Trabalhos futuros

Como evolução da solução desenvolvida, algumas melhorias podem ampliar o escopo do sistema e torná-lo mais útil para a rotina administrativa do estabelecimento:[1]

- incluir controle de faltas, presença e diárias, permitindo incorporar outras regras de cálculo salarial além de adiantamentos e consumo interno;
- registrar ponto diário de entrada e saída dos funcionários, possibilitando análises mais completas de jornada de trabalho;
- adicionar persistência de dados em arquivos ou banco de dados, evitando que os registros fiquem restritos ao código-fonte;
- desenvolver uma interface gráfica para facilitar o cadastro de funcionários, produtos, adiantamentos e consumos sem necessidade de editar classes manualmente;
- gerar relatórios detalhados por período, com histórico de pagamentos, descontos e acompanhamento da evolução salarial dos funcionários.[1]