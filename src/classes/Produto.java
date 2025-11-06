package classes;

import java.util.Date;
// Importar as novas classes de Strategy
import classes.strategy.IDescontoStrategy;
import classes.strategy.SemDesconto;

public class Produto {
    private int codProduto;
    private String nome;
    private float valor; // Este agora é o valor ORIGINAL/BASE
    private boolean alcoolico;
    private String categoria;
    private String descricao;
    private int diaDaPromocao;

    // Atributo do Padrão Strategy
    private IDescontoStrategy descontoStrategy;

    public static String[] categorias = { "Carnes", "Japonesa", "Lanches", "Marmita", "Padarias", "Pizza", "Salgados", "Saudável", "Sorvetes", "Bebidas" };

    public Produto(int codProduto, String nome, float valor, boolean alcoolico, String categoria, String descricao, int diaDaPromocao) {
        this.codProduto = codProduto;
        this.nome = nome;
        this.valor = valor;
        this.alcoolico = alcoolico;
        this.categoria = categoria;
        this.descricao = descricao;
        this.diaDaPromocao = diaDaPromocao;

        // Define uma estratégia padrão para garantir que nunca seja nulo
        this.descontoStrategy = new SemDesconto();
    }


     // Define a estratégia de desconto a ser usada por este produto
    public void setDescontoStrategy(IDescontoStrategy strategy) {
        this.descontoStrategy = strategy;
    }

    // Retorna o valor atual DELEGANDO o cálculo para a estratégia

    public float getValorAtual() {
        // Delega o cálculo para o objeto de estratégia
        return this.descontoStrategy.calcularPreco(this.valor);
    }

    @Override
    public String toString() {
        return String.valueOf(this.codProduto) + ";"
                + this.nome + ";"
                + String.valueOf(this.valor) + ";" // Salva o valor original
                + String.valueOf(this.alcoolico) + ";"
                + this.categoria + ";"
                + this.descricao + ";"
                + String.valueOf(this.diaDaPromocao);
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Este método agora retorna o valor BASE (sem desconto)
     */
    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public boolean getAlcoolico() {
        return alcoolico;
    }

    public void setAlcoolico(boolean alcoolico) {
        this.alcoolico = alcoolico;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDiaDaPromocao() {
        return diaDaPromocao;
    }

    public void setDiaDaPromocao(int diaDaPromocao) {
        this.diaDaPromocao = diaDaPromocao;
    }
}