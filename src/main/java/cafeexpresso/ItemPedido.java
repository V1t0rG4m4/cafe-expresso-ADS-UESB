package cafeexpresso;

/**
 * Representa a relação entre um produto e a quantidade solicitada pelo cliente.
 *
 * Esta classe atua como elo intermediário no pedido: ela isola a lógica de
 * cálculo do subtotal (preço unitário × quantidade), mantendo essa
 * responsabilidade separada das outras classes.
 */
public class ItemPedido {

    private Produto produto;
    private int quantidade;

    /**
     * Cria um novo item de pedido.
     *
     * @param produto    O produto selecionado (não pode ser nulo)
     * @param quantidade A quantidade desejada (deve ser maior que zero)
     */
    public ItemPedido(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.produto = produto;
        this.quantidade = quantidade;
    }

    /**
     * Calcula o subtotal deste item (preço unitário × quantidade).
     *
     * @return O valor total deste item no pedido
     */
    public double calcularSubtotal() {
        return produto.getPrecoUnitario() * quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
