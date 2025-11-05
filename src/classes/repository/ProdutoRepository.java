package classes.repository;

import classes.Produto;
// Importar as estratégias
import classes.strategy.DescontoPromocional;
import java.util.Date; // Importar Date

public class ProdutoRepository extends AbstractRepository<Produto> {

    @Override
    protected String getEntityFileName() {
        return "produtos.txt";
    }

    @Override
    protected String entityToString(Produto entity) {
        return entity.toString();
    }

    @Override
    protected Produto stringToEntity(String line) {
        // Lógica de conversão da linha (split)
        String valores[] = line.split(";");
        int codProduto = Integer.parseInt(valores[0]);
        String nome = valores[1];
        float valor = Float.parseFloat(valores[2]);
        boolean alcoolico = Boolean.valueOf(valores[3]);
        String categoria = valores[4];
        String descricao = valores[5];
        int diaDaPromocao = Integer.parseInt(valores[6]);

        // Cria o objeto
        Produto produto = new Produto(codProduto, nome, valor, alcoolico, categoria, descricao, diaDaPromocao);

        // (NOVO) Define a estratégia ao carregar o produto
        int hoje = new Date().getDay();
        if (hoje == produto.getDiaDaPromocao()) {
            // Se hoje for o dia da promoção, aplica a estratégia
            produto.setDescontoStrategy(new DescontoPromocional());
        }
        // (Não precisamos de "else", pois o construtor do Produto já define "SemDesconto" como padrão)

        return produto;
    }

    @Override
    protected int getEntityId(Produto entity) {
        return entity.getCodProduto();
    }
}