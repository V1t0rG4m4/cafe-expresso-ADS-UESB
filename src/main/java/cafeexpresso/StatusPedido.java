package cafeexpresso;

/**
 * Enumeração que representa os estados possíveis de um pedido.
 * A ordem é: PENDENTE → PAGO → EM_PREPARO → FINALIZADO
 * O sistema deve garantir que nenhuma etapa seja pulada.
 */
public enum StatusPedido {
    PENDENTE,
    PAGO,
    EM_PREPARO,
    FINALIZADO
}
