# ☕ Café Expresso — Sistema de Autoatendimento

> Projeto acadêmico desenvolvido como parte da disciplina de [Engenharia de Software Avançada].

---

## 📋 Descrição do Projeto

O **Café Expresso** é um sistema de autoatendimento desenvolvido para uma cafeteria de pequeno porte. Seu principal objetivo é modernizar e agilizar o processo de realização de pedidos, permitindo que os próprios clientes montem seus pedidos em um **totem (quiosque) digital** e realizem o pagamento no mesmo local — sem precisar enfrentar a fila do caixa.

O sistema foi pensado para melhorar a experiência do cliente, reduzir o tempo de espera e minimizar erros de anotação dos pedidos.

---

## 🖥️ Interfaces do Sistema

O sistema contará com duas interfaces principais:

- **Interface do Cliente** — disponível no totem de autoatendimento, onde o cliente pode consultar o cardápio, montar seu pedido e realizar o pagamento.
- **Interface do Atendente** — disponível no balcão/cozinha, onde o funcionário visualiza e gerencia os pedidos recebidos.

---

## ✅ Requisitos Funcionais

| ID    | Descrição |
|-------|-----------|
| RF01  | O sistema deve permitir o cadastro de produtos com nome e preço. |
| RF02  | O sistema deve permitir adicionar múltiplos itens a um pedido. |
| RF03  | O sistema deve calcular automaticamente o valor total do pedido. |
| RF04  | O sistema deve permitir a alteração do status do pedido: **Pendente → Pago → Em Preparo → Finalizado**. |

---

## 🏗️ Estrutura das Classes

O sistema é estruturado em **três classes principais** que interagem entre si para garantir a integridade dos dados e das regras de negócio:

### `Produto`
Representa os itens disponíveis no cardápio. Define a identidade e o valor unitário de cada café ou acompanhamento. Garante que nenhum item com valor inválido ou nome ausente entre no fluxo de vendas.

**Atributos:** `nome: String`, `precoUnitario: double`  
**Métodos:** `getNome()`, `getPrecoUnitario()`

---

### `ItemPedido`
Atua como elo intermediário entre um produto e a quantidade desejada pelo cliente. Isola a lógica de cálculo parcial (subtotal), garantindo que a multiplicação entre quantidade e preço unitário ocorra de forma precisa.

**Atributos:** `produto: Produto`, `quantidade: int`  
**Métodos:** `calcularSubtotal()`, `getProduto()`, `getQuantidade()`

---

### `Pedido`
Funciona como o núcleo do sistema. Consolida todos os itens do cliente, gerencia o "carrinho de compras" e soma os subtotais para gerar o valor final. Também controla o **fluxo de estados** do atendimento, impedindo avanços indevidos no ciclo do pedido.

**Atributos:** `itens: List<ItemPedido>`, `status: StatusPedido`  
**Métodos:** `adicionarItem()`, `calcularTotal()`, `pagar()`, `enviarParaCozinha()`, `finalizarPedido()`, `getStatus()`, `getItens()`

---

### `StatusPedido` *(Enumeração)*

Define os estados possíveis de um pedido, seguindo obrigatoriamente a ordem abaixo:

```
PENDENTE → PAGO → EM_PREPARO → FINALIZADO
```

> ⚠️ O sistema impede que qualquer etapa seja pulada. Por exemplo, um pedido não pode ir para **EM_PREPARO** sem antes ter o status **PAGO**.

---

## 📐 Diagrama de Classes

```
┌─────────────────────────────────┐        ┌──────────────────────────────┐        ┌──────────────────────────┐
│             Pedido              │        │          ItemPedido          │        │          Produto         │
├─────────────────────────────────┤        ├──────────────────────────────┤        ├──────────────────────────┤
│ - itens: List<ItemPedido>       │ 1    * │ - produto: Produto           │ *    1 │ - nome: String           │
│ - status: StatusPedido          │◄───────│ - quantidade: int            │───────►│ - precoUnitario: double  │
├─────────────────────────────────┤        ├──────────────────────────────┤        ├──────────────────────────┤
│ + Pedido()                      │        │ + ItemPedido(produto,        │        │ + Produto(nome, preco)   │
│ + adicionarItem(...): void      │        │              quantidade)     │        │ + getNome(): String      │
│ + calcularTotal(): double       │        │ + calcularSubtotal(): double │        │ + getPrecoUnitario():    │
│ + pagar(): void                 │        │ + getProduto(): Produto      │        │                  double  │
│ + enviarParaCozinha(): void     │        │ + getQuantidade(): int       │        └──────────────────────────┘
│ + finalizarPedido(): void       │        └──────────────────────────────┘
│ + getStatus(): StatusPedido     │
│ + getItens(): List<ItemPedido>  │        ┌──────────────────────┐
└────────────────┬────────────────┘        │  <<enumeration>>     │
                 │ 1                       │    StatusPedido      │
                 └────────────────────────►├──────────────────────┤
                                      1    │ PENDENTE             │
                                           │ PAGO                 │
                                           │ EM_PREPARO           │
                                           │ FINALIZADO           │
                                           └──────────────────────┘
```

---

## 🚀 Como Executar o Projeto

> *Instruções de execução serão adicionadas conforme o desenvolvimento avança.*

```bash
# Clone o repositório
git clone https://github.com/V1t0rG4m4/cafe-expresso-ADS-UESB.git

# Acesse a pasta do projeto
cd cafe-expresso-ADS-UESB
```

---

## 👥 Equipe

| Nome | Polo |
|------|------|
| [Vitor Gama] | [Ipiaú] |
| [Nome do integrante 2] | [Polo] |

---

## 📅 Cronograma de Entregas

| Semana | Entrega |
|--------|---------|
| Semana 1 | Criação do repositório e README do projeto |
| Semana 2 | *(a definir)* |
| Semana 3 | *(a definir)* |

---

## 📄 Licença

Este projeto é desenvolvido para fins acadêmicos.
