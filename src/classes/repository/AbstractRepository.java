package classes.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe abstrata que implementa o padrão Template Method.
 * Ela define o "esqueleto" dos algoritmos de CRUD (carregar, editar, excluir)
 * **/
public abstract class AbstractRepository<T> {


    //Retorna o nome do arquivo de banco de dados (ex: "clientes.txt").
    protected abstract String getEntityFileName();

    //Converte uma entidade (Objeto) em sua representação em String para o arquivo.
    protected abstract String entityToString(T entity);


    //Converte uma linha do arquivo (String) de volta em uma entidade (Objeto).
    protected abstract T stringToEntity(String line);

    //Retorna o ID da entidade.

    protected abstract int getEntityId(T entity);

    protected void limparArquivo(String path) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        buffWrite.write("");
        buffWrite.close();
    }

    protected String leitor(String path) throws IOException {
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
            String linha = "";
            String ans = "";
            while (true) {
                linha = buffRead.readLine();
                if (linha != null) {
                    ans += linha + "\n";
                } else {
                    break;
                }
            }
            return ans;
        } catch (IOException e) {
            return ""; // Retorna string vazia se o arquivo não existir (primeira execução)
        }
    }

    protected void escritor(String path, String linha) throws IOException {
        String texto;
        try {
            texto = leitor(path) + linha; // Permanece com o que já havia no arquivo
        } catch (IOException ex) {
            texto = linha; // Arquivo novo
        }

        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path))) {
            buffWrite.append(texto + "\n");
        }
    }


     // Armazena (apenda) uma nova entidade ao final do arquivo.
    public void armazenar(T entity) throws IOException {
        escritor(getEntityFileName(), entityToString(entity));
    }


     // Carrega todas as entidades do arquivo para uma lista.

    public ArrayList<T> carregarTodos() throws IOException {
        String texto = leitor(getEntityFileName());

        String[] linhas = texto.split("\n");
        ArrayList<T> entidades = new ArrayList<>();

        for (int i = 0; i < linhas.length; i++) {
            if (linhas[i].length() == 0) continue;
            entidades.add(stringToEntity(linhas[i]));
        }
        return entidades;
    }

    /**
     * TEMPLATE METHOD: Edita uma entidade.
     * A lógica de (ler tudo, limpar, reescrever) é definida aqui.
     */
    public void editar(T entity) throws IOException {
        String path = getEntityFileName();
        String texto = leitor(path);
        limparArquivo(path); // Limpa o arquivo

        String[] linhas = texto.split("\n");
        int entityId = getEntityId(entity);

        for (int i = 0; i < linhas.length; i++) {
            if (linhas[i].length() == 0) continue;

            T currEntity = stringToEntity(linhas[i]);

            if (entityId == getEntityId(currEntity))
                this.armazenar(entity); // Armazena a entidade ATUALIZADA
            else
                this.armazenar(currEntity); // Mantém a entidade antiga
        }
    }

    /**
     * TEMPLATE METHOD: Exclui uma entidade.
     * A lógica de (ler tudo -> limpar -> reescrever) é definida aqui.
     */
    public void excluir(T entity) throws IOException {
        String path = getEntityFileName();
        String texto = leitor(path);
        limparArquivo(path); // Limpa o arquivo

        String[] linhas = texto.split("\n");
        int entityId = getEntityId(entity);

        for (int i = 0; i < linhas.length; i++) {
            if (linhas[i].length() == 0) continue;

            T currEntity = stringToEntity(linhas[i]);

            if (entityId != getEntityId(currEntity))
                this.armazenar(currEntity); // Armazena apenas se NÃO for a entidade para excluir
        }
    }
}