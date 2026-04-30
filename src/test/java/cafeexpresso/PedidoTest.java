package cafeexpresso;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários da classe Pedido.
 *
 * Esta classe de testes cobre dois grupos principais de comportamento:
 *   1. Gestão do carrinho (adicionar itens, calcular total)
 *   2. Controle de estados (fluxo correto e tentativas de transição inválida)
 */
class PedidoTest {

    private Pedido pedido;
    private Produto cafe;
    private Produto croissant;

    @BeforeEach
    void setUp() {
        pedido   = new Pedido();
        cafe     = new Produto("Café Espresso", 5.00);
        croissant = new Produto("Croissant", 7.00);
    }

    // ---------------------------------------------------------------
    // Grupo 1: Gestão do carrinho
    // ---------------------------------------------------------------

    @Test
    void deveComecarComStatusPendente() {
        // Todo pedido recém-criado deve começar como PENDENTE
        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
    }

    @Test
    void deveComecarComCarrinhoVazio() {
        assertTrue(pedido.getItens().isEmpty());
    }

    @Test
    void deveAdicionarItemAoPedido() {
        pedido.adicionarItem(cafe, 2);

        assertEquals(1, pedido.getItens().size());
    }

    @Test
    void deveAdicionarMultiplosItensAoPedido() {
        pedido.adicionarItem(cafe, 2);
        pedido.adicionarItem(croissant, 1);

        assertEquals(2, pedido.getItens().size());
    }

    @Test
    void deveCalcularTotalComUmItem() {
        // 2 cafés a R$5,00 = R$10,00
        pedido.adicionarItem(cafe, 2);

        assertEquals(10.00, pedido.calcularTotal());
    }

    @Test
    void deveCalcularTotalComMultiplosItens() {
        // 2 cafés (R$5,00) + 1 croissant (R$7,00) = R$17,00
        pedido.adicionarItem(cafe, 2);
        pedido.adicionarItem(croissant, 1);

        assertEquals(17.00, pedido.calcularTotal());
    }

    @Test
    void deveRetornarTotalZeroParaCarrinhoVazio() {
        assertEquals(0.00, pedido.calcularTotal());
    }

    // ---------------------------------------------------------------
    // Grupo 2: Fluxo correto de estados
    // ---------------------------------------------------------------

    @Test
    void deveAlterarStatusParaPagoAoRealizarPagamento() {
        pedido.pagar();

        assertEquals(StatusPedido.PAGO, pedido.getStatus());
    }

    @Test
    void deveAlterarStatusParaEmPreparoAoEnviarParaCozinha() {
        pedido.pagar();
        pedido.enviarParaCozinha();

        assertEquals(StatusPedido.EM_PREPARO, pedido.getStatus());
    }

    @Test
    void deveAlterarStatusParaFinalizadoAoFinalizar() {
        pedido.pagar();
        pedido.enviarParaCozinha();
        pedido.finalizarPedido();

        assertEquals(StatusPedido.FINALIZADO, pedido.getStatus());
    }

    // ---------------------------------------------------------------
    // Grupo 3: Transições de estado INVÁLIDAS
    // (regras de negócio que o sistema deve impedir)
    // ---------------------------------------------------------------

    @Test
    void naoDevePermitirEnviarParaCozinhaSemPagamento() {
        // Tenta pular PENDENTE → EM_PREPARO sem passar por PAGO
        assertThrows(IllegalStateException.class, () -> {
            pedido.enviarParaCozinha();
        });
    }

    @Test
    void naoDevePermitirFinalizarSemEstarEmPreparo() {
        // Tenta pular direto para FINALIZADO sem as etapas anteriores
        assertThrows(IllegalStateException.class, () -> {
            pedido.finalizarPedido();
        });
    }

    @Test
    void naoDevePermitirPagarUmPedidoJaPago() {
        pedido.pagar();

        // Tenta pagar de novo — o sistema deve recusar
        assertThrows(IllegalStateException.class, () -> {
            pedido.pagar();
        });
    }

    @Test
    void naoDevePermitirAdicionarItemAposPagamento() {
        pedido.pagar();

        // Após o pagamento, o carrinho deve estar "fechado"
        assertThrows(IllegalStateException.class, () -> {
            pedido.adicionarItem(cafe, 1);
        });
    }
}
