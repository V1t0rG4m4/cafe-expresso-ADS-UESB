package cafeexpresso;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários da classe ItemPedido.
 *
 * Usamos @BeforeEach para criar um produto reutilizável entre os testes,
 * evitando repetição de código.
 */
class ItemPedidoTest {

    // Produto que será reutilizado nos testes
    private Produto produto;

    @BeforeEach
    void setUp() {
        // Este método roda automaticamente antes de cada teste
        produto = new Produto("Cappuccino", 8.00);
    }

    // ---------------------------------------------------------------
    // Testes de criação bem-sucedida
    // ---------------------------------------------------------------

    @Test
    void deveCriarItemPedidoComProdutoEQuantidadeValidos() {
        ItemPedido item = new ItemPedido(produto, 2);

        assertEquals(produto, item.getProduto());
        assertEquals(2, item.getQuantidade());
    }

    // ---------------------------------------------------------------
    // Testes do cálculo de subtotal
    // ---------------------------------------------------------------

    @Test
    void deveCalcularSubtotalCorretamente() {
        // Cappuccino custa R$8,00. Com 3 unidades, o subtotal deve ser R$24,00
        ItemPedido item = new ItemPedido(produto, 3);

        assertEquals(24.00, item.calcularSubtotal());
    }

    @Test
    void deveCalcularSubtotalParaUmaUnidade() {
        // Com 1 unidade, o subtotal deve ser igual ao preço unitário
        ItemPedido item = new ItemPedido(produto, 1);

        assertEquals(8.00, item.calcularSubtotal());
    }

    // ---------------------------------------------------------------
    // Testes de validação
    // ---------------------------------------------------------------

    @Test
    void deveLancarExcecaoQuandoProdutoForNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ItemPedido(null, 2);
        });
    }

    @Test
    void deveLancarExcecaoQuandoQuantidadeForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ItemPedido(produto, 0);
        });
    }

    @Test
    void deveLancarExcecaoQuandoQuantidadeForNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ItemPedido(produto, -1);
        });
    }
}
