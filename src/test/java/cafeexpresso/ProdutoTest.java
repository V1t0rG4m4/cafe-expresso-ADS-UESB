package cafeexpresso;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários da classe Produto.
 *
 * Cada método de teste verifica um comportamento específico da classe.
 * O nome do método descreve exatamente o que está sendo testado —
 * isso é uma boa prática, pois o nome do teste é a primeira coisa
 * que você vê quando ele falha.
 */
class ProdutoTest {

    // ---------------------------------------------------------------
    // Testes de criação bem-sucedida
    // ---------------------------------------------------------------

    @Test
    void deveCriarProdutoComNomeEPrecoValidos() {
        // Arrange: prepara os dados do teste
        Produto produto = new Produto("Café Espresso", 5.50);

        // Assert: verifica se o resultado é o esperado
        assertEquals("Café Espresso", produto.getNome());
        assertEquals(5.50, produto.getPrecoUnitario());
    }

    // ---------------------------------------------------------------
    // Testes de validação — nome inválido
    // ---------------------------------------------------------------

    @Test
    void deveLancarExcecaoQuandoNomeForNulo() {
        // Verifica se o sistema rejeita corretamente um nome nulo
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto(null, 5.50);
        });
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("", 5.50);
        });
    }

    @Test
    void deveLancarExcecaoQuandoNomeForApenasEspacos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("   ", 5.50);
        });
    }

    // ---------------------------------------------------------------
    // Testes de validação — preço inválido
    // ---------------------------------------------------------------

    @Test
    void deveLancarExcecaoQuandoPrecoForZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Café", 0);
        });
    }

    @Test
    void deveLancarExcecaoQuandoPrecoForNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Produto("Café", -3.00);
        });
    }
}
