package cafeexpresso;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um pedido realizado pelo cliente no totem de autoatendimento.
 *
 * Esta classe é o núcleo do sistema. Ela é responsável por:
 *   1. Gerenciar o carrinho de compras (lista de itens)
 *   2. Calcular o valor total somando os subtotais de cada item
 *   3. Controlar o fluxo de estados, impedindo transições inválidas
 *
 * O ciclo de vida de um pedido segue obrigatoriamente esta ordem:
 *   PENDENTE → PAGO → EM_PREPARO → FINALIZADO
 */
public class Pedido {

    private List<ItemPedido> itens;
    private StatusPedido status;

    /**
     * Cria um novo pedido. Todo pedido começa no estado PENDENTE.
     */
    public Pedido() {
        this.itens = new ArrayList<>();
        this.status = StatusPedido.PENDENTE;
    }

    /**
     * Adiciona um item ao pedido.
     * Só é permitido adicionar itens enquanto o pedido estiver PENDENTE.
     *
     * @param produto    O produto a ser adicionado
     * @param quantidade A quantidade desejada
     */
    public void adicionarItem(Produto produto, int quantidade) {
        if (status != StatusPedido.PENDENTE) {
            throw new IllegalStateException(
                "Não é possível adicionar itens: o pedido não está mais no estado PENDENTE."
            );
        }
        itens.add(new ItemPedido(produto, quantidade));
    }

    /**
     * Calcula o valor total do pedido somando os subtotais de todos os itens.
     *
     * @return O valor total a ser pago pelo cliente
     */
    public double calcularTotal() {
        // Percorre todos os itens e soma seus subtotais
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    /**
     * Registra o pagamento do pedido.
     * Transição: PENDENTE → PAGO
     */
    public void pagar() {
        if (status != StatusPedido.PENDENTE) {
            throw new IllegalStateException(
                "O pedido só pode ser pago quando estiver no estado PENDENTE. " +
                "Estado atual: " + status
            );
        }
        this.status = StatusPedido.PAGO;
    }

    /**
     * Envia o pedido para a cozinha iniciar o preparo.
     * Transição: PAGO → EM_PREPARO
     *
     * Regra de negócio: um pedido não pode ir para a cozinha sem confirmação de pagamento.
     */
    public void enviarParaCozinha() {
        if (status != StatusPedido.PAGO) {
            throw new IllegalStateException(
                "O pedido só pode ir para a cozinha após o pagamento. " +
                "Estado atual: " + status
            );
        }
        this.status = StatusPedido.EM_PREPARO;
    }

    /**
     * Finaliza o pedido, indicando que foi preparado e entregue ao cliente.
     * Transição: EM_PREPARO → FINALIZADO
     */
    public void finalizarPedido() {
        if (status != StatusPedido.EM_PREPARO) {
            throw new IllegalStateException(
                "O pedido só pode ser finalizado quando estiver em preparo. " +
                "Estado atual: " + status
            );
        }
        this.status = StatusPedido.FINALIZADO;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }
}
