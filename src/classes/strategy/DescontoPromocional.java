package classes.strategy;

/**
 * Estratégia concreta para o desconto promocional padrão (5%).
 */
public class DescontoPromocional implements IDescontoStrategy {

    // A lógica de 5% agora vive aqui, isolada.
    private final float taxaDesconto = 0.95f;

    @Override
    public float calcularPreco(float precoOriginal) {
        return precoOriginal * taxaDesconto;
    }
}