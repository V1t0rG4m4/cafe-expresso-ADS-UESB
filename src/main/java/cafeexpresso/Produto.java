package cafeexpresso;

/**
 * Representa um produto disponível no cardápio da cafeteria.
 *
 * Esta classe é a base de dados do sistema: define o nome e o
 * valor unitário de cada item. As validações no construtor garantem
 * que nenhum produto inválido entre no fluxo de vendas.
 */
public class Produto {

    private String nome;
    private double precoUnitario;

    /**
     * Cria um novo produto com nome e preço.
     *
     * @param nome          O nome do produto (não pode ser vazio ou nulo)
     * @param precoUnitario O preço unitário (deve ser maior que zero)
     */
    public Produto(String nome, double precoUnitario) {
        // Garante que o produto sempre terá um nome válido
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        // Garante que o produto não terá preço inválido (zero ou negativo)
        if (precoUnitario <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }
        this.nome = nome;
        this.precoUnitario = precoUnitario;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }
}
