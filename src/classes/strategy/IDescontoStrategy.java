package classes.strategy;

/**
 * Padrão Strategy: Interface para as diferentes
 * lógicas de cálculo de desconto.
 */
public interface IDescontoStrategy {
    /**
     * Calcula o preço final de um produto com base no preço original.
     * @param precoOriginal O preço base do produto.
     * @return O preço com o desconto aplicado.
     */
    float calcularPreco(float precoOriginal);
}