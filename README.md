# 🏥 Reserva de Equipamentos - WebService SOAP (reserva-webservice)

WebService SOAP para gerenciamento de reservas de equipamentos de laboratório, desenvolvido em Java 21 com JAX-WS, sem frameworks como Spring Boot.

Projeto desenvolvido para o **Checkpoint 1** da disciplina de **Arquitetura SOA / Web Services** — FIAP, sob orientação do **Prof. Salatiel Luz Marinho**.

---

## 📹 Link vídeo de apresentação no Youtube:

https://youtu.be/Z2rcsU3OZ3M

---

## 📋 Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Tecnologias](#tecnologias)
- [Equipamentos Cadastrados](#equipamentos-cadastrados)
- [Operações SOAP](#operações-soap)
- [Como Executar](#como-executar)
- [Testando no Insomnia](#testando-no-insomnia)
- [Testes Unitários](#testes-unitários)
- [Melhorias Implementadas](#melhorias-implementadas)
- [Equipe](#equipe)

---

## Sobre o Projeto

O exercício original solicitava a criação de um WebService SOAP com 4 pacotes simples (`model`, `service`, `repository`, `publisher`) e 3 operações: `listarEquipamentos()`, `reservarEquipamento()` e `cancelarReserva()`.

O projeto foi evoluído para uma arquitetura profissional em camadas, aplicando princípios de SOLID, Domain Model Pattern e boas práticas de design de software — com 4 operações SOAP, validações de negócio robustas e 21 testes unitários.

---

## Estrutura do Projeto

```
reserva-webservice
│
├── pom.xml
│
└── src
    ├── main
    │   └── java
    │       └── br.com.fiap.reserva
    │           │
    │           ├── domain
    │           │   ├── model
    │           │   │   ├── Equipamento.java
    │           │   │   ├── Reserva.java
    │           │   │   └── StatusReserva.java
    │           │   └── repository
    │           │       ├── EquipamentoRepository.java
    │           │       └── ReservaRepository.java
    │           │
    │           ├── application
    │           │   ├── dto
    │           │   │   └── ReservaResponse.java
    │           │   └── service
    │           │       └── ReservaApplicationService.java
    │           │
    │           ├── infrastructure
    │           │   └── repository
    │           │       └── memory
    │           │           ├── EquipamentoRepositoryMemory.java
    │           │           └── ReservaRepositoryMemory.java
    │           │
    │           ├── presentation
    │           │   └── soap
    │           │       └── ReservaEndpoint.java
    │           │
    │           └── publisher
    │               └── ServicePublisher.java
    │
    └── test
        └── java
            └── br.com.fiap.reserva
                ├── SuiteDeTestesGeral.java
                ├── domain
                │   └── model
                │       ├── EquipamentoTest.java
                │       └── ReservaTest.java
                ├── application
                │   └── service
                │       └── ReservaApplicationServiceTest.java
                └── infrastructure
                    └── repository
                        └── memory
                            ├── EquipamentoRepositoryMemoryTest.java
                            └── ReservaRepositoryMemoryTest.java
```

### Responsabilidade de cada camada

| Camada | Pacote | Responsabilidade |
|---|---|---|
| **Domain** | `domain/model` e `domain/repository` | Regras de negócio puras e contratos dos repositórios |
| **Application** | `application/service` e `application/dto` | Orquestra os casos de uso e define o DTO de resposta |
| **Infrastructure** | `infrastructure/repository/memory` | Implementações técnicas dos repositórios em memória |
| **Presentation** | `presentation/soap` | Fachada SOAP — expõe as operações via JAX-WS |
| **Publisher** | `publisher` | Ponto de entrada — sobe o servidor HTTP embutido |

---

## Tecnologias

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 21 | Linguagem principal |
| JAX-WS (`jaxws-rt`) | 2.3.7 | Criação, publicação e geração de WSDL do WebService SOAP |
| JAXB (`jaxb-api`) | 2.3.1 | Serialização de objetos Java para XML e vice-versa |
| JAXB Runtime (`jaxb-runtime`) | 2.3.8 | Implementação em runtime do JAXB |
| Jakarta Activation | 1.2.2 | Suporte necessário para o JAXB funcionar corretamente |
| JUnit Jupiter | 5.11.4 | Testes unitários |
| JUnit Platform Suite Engine | 1.11.4 | Suite de testes com `@Suite` e `@SelectPackages` |
| Maven | — | Gerenciamento de dependências e build |
| IntelliJ IDEA | — | IDE de desenvolvimento |

---

## Equipamentos Cadastrados

O sistema inicializa com 5 equipamentos de laboratório pré-cadastrados em memória:

| ID | Nome | Descrição |
|---|---|---|
| 1 | Microscópio Óptico | Microscópio com ampliação de até 1000x |
| 2 | Computador Dell | Computador para análise de dados laboratoriais |
| 3 | Centrífuga | Centrífuga de alta rotação para amostras |
| 4 | Espectrofotômetro | Medição de absorbância de soluções |
| 5 | Balança Analítica | Balança de precisão 0,0001g |

---

## Operações SOAP

O serviço expõe 4 operações no endpoint `http://localhost:8080/reserva`:

| Operação | Parâmetros | Retorno | Descrição |
|---|---|---|---|
| `listarEquipamentos` | — | `List<Equipamento>` | Lista todos os equipamentos com disponibilidade atual |
| `criarReserva` | `equipamentoId`, `responsavel` | `ReservaResponse` | Reserva um equipamento disponível |
| `cancelarReserva` | `reservaId` | `ReservaResponse` | Cancela uma reserva ativa e devolve o equipamento |
| `listarHistoricoReservas` | — | `List<Reserva>` | Lista todas as reservas com seus status (`ATIVA` / `CANCELADA`) |

### Validações de negócio

**`criarReserva`:**
- Retorna erro `"Equipamento não encontrado"` se o ID não existir
- Retorna erro `"Equipamento indisponível"` se o equipamento já estiver reservado

**`cancelarReserva`:**
- Retorna erro `"Reserva não encontrada"` se o ID não existir
- Retorna erro `"Reserva já cancelada"` se a reserva já tiver sido cancelada

---

## Como Executar

### Pré-requisitos

- Java 21 instalado
- Maven configurado
- IntelliJ IDEA (ou outra IDE)

### Passo a passo

**1.** Clone ou abra o projeto no IntelliJ IDEA.

**2.** Deixe o Maven baixar as dependências automaticamente (ou rode `mvn install`).

**3.** Execute o `main` da classe `ServicePublisher`:

```
br.com.fiap.reserva.publisher.ServicePublisher
```

**4.** Confirme no console que o serviço subiu:

```
SOAP WebService rodando em:
http://localhost:8080/reserva?wsdl
```

**5.** Acesse o WSDL no navegador para confirmar:

```
http://localhost:8080/reserva?wsdl
```

O WSDL é o contrato do serviço — ele define todas as operações, tipos de dados, endpoint e protocolos disponíveis.

---

## Testando no Insomnia

### Configuração base

Antes de qualquer teste, certifique que o `ServicePublisher` está rodando no IntelliJ. Você deve ver no console:

```
SOAP WebService rodando em:
http://localhost:8080/reserva?wsdl
```

Toda requisição usa a mesma configuração:

| Campo | Valor |
|---|---|
| Método | `POST` |
| URL | `http://localhost:8080/reserva` |
| Header (chave) | `Content-Type`                    |
| Header (valor) | `text/xml;charset=UTF-8`          |

> No Insomnia, selecione `XML` na aba **Body** ao colar os XMLs abaixo.

---

### Requisição 1 — listarEquipamentos

#### Como configurar no Insomnia
1. Clique em **New Request**
2. Nomeie como `listarEquipamentos`
3. Método: `POST`
4. URL: `http://localhost:8080/reserva`
5. Aba **Headers** → adicione `Content-Type: text/xml;charset=UTF-8`
6. Aba **Body** → selecione `XML` e cole o XML abaixo

#### Body

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:ser="http://soap.presentation.reserva.fiap.com.br/">
    <soapenv:Header/>
    <soapenv:Body>
        <ser:listarEquipamentos/>
    </soapenv:Body>
</soapenv:Envelope>
```

#### **Resposta esperada** — todos os 5 equipamentos com `disponivel: true`

```xml
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
    <S:Body>
        <ns2:listarEquipamentosResponse xmlns:ns2="http://soap.presentation.reserva.fiap.com.br/">
            <return>
                <descricao>Microscópio com ampliação de até 1000x</descricao>
                <disponivel>true</disponivel>
                <id>1</id>
                <nome>Microscópio Óptico</nome>
            </return>
            <return>
                <descricao>Computador para análise de dados laboratoriais</descricao>
                <disponivel>true</disponivel>
                <id>2</id>
                <nome>Computador Dell</nome>
            </return>
            <!-- ... demais equipamentos ... -->
        </ns2:listarEquipamentosResponse>
    </S:Body>
</S:Envelope>
```

#### Print de tela

<img width="1200" height="585" alt="image" src="https://github.com/user-attachments/assets/69fb4858-ccc2-46e8-b247-ccce3e540887" />


---

### Requisição 2 — criarReserva

#### Como configurar no Insomnia
1. Clique em **New Request**
2. Nomeie como `criarReserva`
3. Método: `POST`
4. URL: `http://localhost:8080/reserva`
5. Aba **Headers** → adicione `Content-Type: text/xml;charset=UTF-8`
6. Aba **Body** → selecione `XML` e cole o XML abaixo

#### **Cenário A — Sucesso (equipamento disponível)**

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:ser="http://soap.presentation.reserva.fiap.com.br/">
    <soapenv:Header/>
    <soapenv:Body>
        <ser:criarReserva>
            <equipamentoId>1</equipamentoId>
            <responsavel>Renan</responsavel>
        </ser:criarReserva>
    </soapenv:Body>
</soapenv:Envelope>
```

**Resposta esperada — reserva criada com `status: ATIVA`:**

```xml
<ns2:criarReservaResponse>
    <return>
        <reserva>
            <equipamentoId>1</equipamentoId>
            <id>1</id>
            <responsavel>Renan</responsavel>
            <status>ATIVA</status>
        </reserva>
    </return>
</ns2:criarReservaResponse>
```

> ⚠️ Anote o `id` da reserva retornado — você vai precisar dele no `cancelarReserva`.

#### Print de tela

<img width="1200" height="585" alt="image" src="https://github.com/user-attachments/assets/280c2aec-62ae-4859-8167-bf4c78710058" />


#### **Cenário B — Equipamento indisponível:** 

Envie a mesma requisição (XML) do Cenário A novamente, sem alterar nada. Retorna `erro: Equipamento indisponível`.

**Resposta esperada:**

```xml
<return>
    <erro>Equipamento indisponível</erro>
</return>
```

#### Print de tela

<img width="1200" height="585" alt="image" src="https://github.com/user-attachments/assets/9d668bdf-6a1a-47a4-a702-21c9410b89c9" />


#### **Cenário C — Equipamento inexistente:** 

Altere o `equipamentoId` para um ID que não existe (ex: `99`):

```xml
<ser:criarReserva>
    <equipamentoId>99</equipamentoId>
    <responsavel>Renan</responsavel>
</ser:criarReserva>
```

Retorna `erro: Equipamento não encontrado`.

**Resposta esperada:**

```xml
<return>
    <erro>Equipamento não encontrado</erro>
</return>
```

#### Print de tela

<img width="1200" height="585" alt="image" src="https://github.com/user-attachments/assets/5e29da6c-fafb-4174-ae81-34dd6d2a63d7" />

---

### Requisição 3 — cancelarReserva

#### Como configurar no Insomnia
1. Clique em **New Request**
2. Nomeie como `cancelarReserva`
3. Método: `POST`
4. URL: `http://localhost:8080/reserva`
5. Aba **Headers** → adicione `Content-Type: text/xml;charset=UTF-8`
6. Aba **Body** → selecione `XML` e cole o XML abaixo

---

#### **Cenário A — Sucesso (reserva ativa)**

Use o `id` retornado no `criarReserva` (Cenário A):

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:ser="http://soap.presentation.reserva.fiap.com.br/">
    <soapenv:Header/>
    <soapenv:Body>
        <ser:cancelarReserva>
            <reservaId>1</reservaId>
        </ser:cancelarReserva>
    </soapenv:Body>
</soapenv:Envelope>
```

**Resposta esperada:** Retorna a reserva com `status: CANCELADA` e devolve o equipamento ao estoque.

```xml
<ns2:cancelarReservaResponse>
    <return>
        <reserva>
            <equipamentoId>1</equipamentoId>
            <id>1</id>
            <responsavel>Renan</responsavel>
            <status>CANCELADA</status>
        </reserva>
    </return>
</ns2:cancelarReservaResponse>
```

#### Print de tela

<img width="1200" height="585" alt="image" src="https://github.com/user-attachments/assets/9762de1f-54d4-4d09-bc46-b0993dc9688b" />

---

#### **Cenário B — Reserva já cancelada** 

Envie a mesma requisição (XML) do cenário A novamente. Retorna `erro: Reserva já cancelada`.

**Resposta esperada:**

```xml
<return>
    <erro>Reserva já cancelada</erro>
</return>
```

#### Print de tela

<img width="1200" height="585" alt="image" src="https://github.com/user-attachments/assets/ea0e844b-841c-41a9-92f6-e5cfbbdcf1e4" />

---


#### **Cenário C — Reserva inexistente** 

Altere o `reservaId`para um ID que não existe (ex: `99`). Retorna `erro: Reserva não encontrada`.

```xml
<ser:cancelarReserva>
    <reservaId>99</reservaId>
</ser:cancelarReserva>
```

**Resposta esperada:**

```xml
<return>
    <erro>Reserva não encontrada</erro>
</return>
```

#### Print de tela

<img width="1200" height="585" alt="image" src="https://github.com/user-attachments/assets/264d987a-d0bc-4ea3-9849-bffd7ebeba82" />

---

### Requisição 4 — listarHistoricoReservas

#### Como configurar no Insomnia
1. Clique em **New Request**
2. Nomeie como `listarHistoricoReservas`
3. Método: `POST`
4. URL: `http://localhost:8080/reserva`
5. Aba **Headers** → adicione `Content-Type: text/xml;charset=UTF-8`
6. Aba **Body** → selecione `XML` e cole o XML abaixo

#### Body

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:ser="http://soap.presentation.reserva.fiap.com.br/">
    <soapenv:Header/>
    <soapenv:Body>
        <ser:listarHistoricoReservas/>
    </soapenv:Body>
</soapenv:Envelope>
```

#### **Resposta esperada:** todas as reservas criadas na sessão com seus respectivos status.

```xml
<ns2:listarHistoricoReservasResponse>
    <return>
        <equipamentoId>1</equipamentoId>
        <id>1</id>
        <responsavel>Renan</responsavel>
        <status>CANCELADA</status>
    </return>
    <!-- demais reservas criadas durante os testes -->
</ns2:listarHistoricoReservasResponse>
```

#### Print de tela

<img width="1200" height="585" alt="image" src="https://github.com/user-attachments/assets/b0a0f4c9-2128-442a-9cb6-93ac2c236834" />

---

### Fluxo completo de teste(Ciclo Recomendado)

Execute nessa ordem para demonstrar o ciclo completo do sistema:

| Passo | Requisição | Ação | O que verificar |
|---|---|---|---|
| 1 | `listarEquipamentos` | Rodar sem alterar nada | Todos os 5 com `disponivel: true` |
| 2 | `criarReserva` | `equipamentoId=1`, `responsavel=Renan` | `status: ATIVA`, anote o `id` |
| 3 | `listarEquipamentos` | Rodar sem alterar nada | Equipamento 1 com `disponivel: false` |
| 4 | `criarReserva` | Mesmo `equipamentoId=1` | Erro `Equipamento indisponível` |
| 5 | `criarReserva` | `equipamentoId=99` | Erro `Equipamento não encontrado` |
| 6 | `cancelarReserva` | `reservaId` anotado no passo 2 | `status: CANCELADA` |
| 7 | `cancelarReserva` | Mesmo `reservaId` do passo 6 | Erro `Reserva já cancelada` |
| 8 | `listarEquipamentos` | Rodar sem alterar nada | Equipamento 1 volta com `disponivel: true` |
| 9 | `listarHistoricoReservas` | Rodar sem alterar nada | Todas as reservas com seus status |

---

## Testes Unitários

O projeto conta com **21 testes unitários** organizados em 5 classes, seguindo o padrão JUnit 5 com `@DisplayName` e `@BeforeEach`.

| Classe de Teste | Testes | O que cobre |
|---|---|---|
| `EquipamentoTest` | 4 | Criação, `indisponibilizar()`, `disponibilizar()`, construtor vazio |
| `ReservaTest` | 4 | Criação com status `ATIVA`, `cancelar()`, cancelar já cancelada, construtor vazio |
| `ReservaRepositoryMemoryTest` | 4 | Salvar com ID automático, buscar por ID, ID inexistente, listar |
| `EquipamentoRepositoryMemoryTest` | 4 | Listar 5 equipamentos, buscar por ID, ID inexistente, todos disponíveis |
| `ReservaApplicationServiceTest` | 5 | Criar reserva (sucesso, indisponível, inexistente), cancelar (sucesso, já cancelada) |

### Como rodar os testes

**Via IntelliJ:** clique com botão direito em `SuiteDeTestesGeral` → **Run**.

**Via Maven:**
```bash
mvn test
```

A `SuiteDeTestesGeral` utiliza `@Suite` e `@SelectPackages` para executar todos os testes do projeto de uma única vez, cobrindo as camadas `domain`, `application` e `infrastructure`.

---

## Melhorias Implementadas

O projeto partiu da estrutura básica solicitada no exercício — 4 pacotes simples (`model`, `service`, `repository`, `publisher`) — e foi evoluído com as seguintes melhorias:

---

### 1. Arquitetura em camadas

A estrutura original previa um único `ReservaService` achatado. O projeto foi refatorado para **4 camadas com responsabilidades bem definidas**:

```
domain/          → regras de negócio puras (modelos e interfaces)
application/     → orquestra os casos de uso
infrastructure/  → implementações técnicas (repositórios em memória)
presentation/    → fachada SOAP (ReservaEndpoint)
```

---

### 2. Interfaces para os repositórios (Princípio DIP — SOLID)

`EquipamentoRepository` e `ReservaRepository` foram definidos como **interfaces** no domínio. As implementações concretas ficam em `infrastructure/repository/memory`. Isso aplica o **Dependency Inversion Principle** — o service depende da abstração, não da implementação. Para trocar por um banco de dados real, basta criar uma nova implementação sem tocar em nenhuma outra camada.

---

### 3. `ReservaResponse` com factory methods

Em vez de retornar a `Reserva` diretamente ou uma `String` de status, foi criado um DTO com dois factory methods estáticos:

```java
ReservaResponse.sucesso(reserva)
ReservaResponse.erro("mensagem")
```

Expressivo e padronizado — todas as respostas de `criarReserva` e `cancelarReserva` seguem o mesmo contrato.

---

### 4. Domain Model Pattern

A lógica de negócio foi movida para dentro dos próprios modelos, em vez de ficar no service:

- `Equipamento` expõe `indisponibilizar()` e `disponibilizar()` — encapsulando a mudança de estado
- `Reserva` expõe `cancelar()` — com validação embutida que impede cancelar duas vezes

```java
public void cancelar() {
    if (this.status == StatusReserva.CANCELADA) {
        throw new RuntimeException("Reserva já cancelada");
    }
    this.status = StatusReserva.CANCELADA;
}
```

---

### 5. `StatusReserva` como enum

Em vez de `String status = "ATIVA"`, foi criado um enum `StatusReserva` com os valores `ATIVA` e `CANCELADA`. Isso elimina erros de digitação e torna o código mais seguro em tempo de compilação.

---

### 6. `ReservaEndpoint` como fachada SOAP (Princípio SRP — SOLID)

O `@WebService` não contém nenhuma lógica de negócio — apenas recebe as chamadas SOAP e delega para o `ReservaApplicationService`. Isso respeita o **Single Responsibility Principle**: o endpoint só sabe publicar operações, o service só sabe executá-las.

---

### 7. `@XmlRootElement` e construtores vazios

Todas as classes trafegadas pelo SOAP (`Equipamento`, `Reserva`, `ReservaResponse`) receberam a anotação `@XmlRootElement` e construtores sem argumentos, garantindo que o JAXB consiga serializar e desserializar os objetos corretamente para XML.

---

### 8. `@WebParam` com nomes explícitos

Todos os parâmetros das operações SOAP receberam `@WebParam(name = "...")`, garantindo nomes legíveis no XML e no WSDL — sem os genéricos `arg0`, `arg1` que o JAX-WS geraria por padrão.

---

### 9. Instância compartilhada dos repositórios

O `ReservaEndpoint` mantém `equipamentoRepo` e `reservaRepo` como atributos, passando as **mesmas instâncias** para o `ReservaApplicationService`. Isso garante que `listarEquipamentos()` e `listarHistoricoReservas()` reflitam o estado real após cada `criarReserva` ou `cancelarReserva`.

---

### 10. Operação `listarHistoricoReservas()` adicional

Além das 3 operações solicitadas no exercício, foi adicionada a operação `listarHistoricoReservas()`, que exibe todas as reservas da sessão com seus respectivos status (`ATIVA` ou `CANCELADA`), permitindo rastrear o ciclo completo de cada reserva.

---

### 11. Tratamento de erros sem stacktrace no console

A validação `"Reserva já cancelada"` foi movida do `throw new RuntimeException` (que gerava log `GRAVE` no console do IntelliJ) para uma verificação antecipada no `ReservaApplicationService`, retornando um `ReservaResponse.erro()` padronizado — mantendo o console limpo e a resposta consistente com os demais casos de erro.

---

## **Equipe**

### **Desenvolvedores (ICERS)**

- **Renan Dias Utida - RM 558540**
- **Camila Pedroza da Cunha - RM 558768**
- **Isabelle Dallabeneta Carlesso - RM554592**
- **Nicoli Amy Kassa - RM 559104**
- **Pedro Almeida e Camacho - RM 556831**

---

- **Instituição:** FIAP - Faculdade de Informática e Administração Paulista
- **Disciplina:** Arquitetura Orientada a Serviços (SOA) e Web Services
- **Professor:** Salatiel Luz Marinho
- **Sala:** 3ESPW

**[⬆ Voltar ao topo](#-reserva-de-equipamentos---webservice-soap-reserva-webservice)**
