package classes.factory;

import classes.Produto;
import classes.ManipuladorArquivo;
// Importar as estratégias
import classes.strategy.DescontoPromocional;
import classes.strategy.SemDesconto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date; // Importar Date

// padrão Factory: Centraliza a lógica de criação de produtos


public class ProdutoFactory {

    public static Produto createProduto(String nome, float valor, boolean alcoolico, String categoria, String descricao, int diaDaPromocao) throws IOException {
        int curr_id = 0;
        try {
            ArrayList<Produto> produtos = ManipuladorArquivo.carregarProdutos();
            if (produtos.size() > 0) {
                curr_id = produtos.get(produtos.size()-1).getCodProduto() + 1;
            }
        } catch(IOException ex) {
            curr_id = 0;
        }

        // 2. Cria o objeto
        Produto novoProduto = new Produto(curr_id, nome, valor, alcoolico, categoria, descricao, diaDaPromocao);

        // 3. (NOVO) Define a estratégia de desconto
        int hoje = new Date().getDay();
        if (hoje == diaDaPromocao) {
            // Se hoje é o dia da promoção, injeta a estratégia de desconto
            novoProduto.setDescontoStrategy(new DescontoPromocional());
        }
        // (Não precisamos de "else", pois o construtor do Produto já define "SemDesconto" como padrão)

        // 4. Persiste o objeto
        ManipuladorArquivo.armazenar(novoProduto);

        // 5. Retorna o objeto criado
        return novoProduto;
    }
}