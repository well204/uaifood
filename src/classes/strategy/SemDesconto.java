package classes.strategy;

/**
 * Estratégia concreta para quando não há desconto.
 */
public class SemDesconto implements IDescontoStrategy {
    @Override
    public float calcularPreco(float precoOriginal) {
        return precoOriginal;
    }
}