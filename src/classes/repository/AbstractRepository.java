package classes.repository;

import classes.util.FileHandler; // Importar a nova Facade
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; // Importar List

/**
 * Classe abstrata (Template Method) refatorada para usar a Facade FileHandler.
 * Agora ela se concentra APENAS na lógica de template (o "como" editar/excluir)
 * e delega o I/O de baixo nível para a facade.
 */
public abstract class AbstractRepository<T> {

    // --- Métodos "Hook" Abstratos (implementados pelas subclasses) ---

    protected abstract String getEntityFileName();
    protected abstract String entityToString(T entity);
    protected abstract T stringToEntity(String line);
    protected abstract int getEntityId(T entity);

    //
    // Os métodos de I/O (leitor, escritor, limparArquivo) FORAM REMOVIDOS.
    //


    // --- Métodos de CRUD (Template Methods e outros) ---

    /**
     * Armazena uma nova entidade.
     * Agora usa o método 'appendLine' da facade.
     */
    public void armazenar(T entity) throws IOException {
        FileHandler.appendLine(getEntityFileName(), entityToString(entity));
    }

    /**
     * Carrega todas as entidades.
     * Agora usa o método 'readAllLines' da facade.
     */
    public ArrayList<T> carregarTodos() throws IOException {
        List<String> linhas = FileHandler.readAllLines(getEntityFileName());

        ArrayList<T> entidades = new ArrayList<>();
        for (String linha : linhas) {
            // A verificação de linha vazia já é feita no FileHandler
            entidades.add(stringToEntity(linha));
        }
        return entidades;
    }

    /**
     * TEMPLATE METHOD: Edita uma entidade.
     * Refatorado para ser muito mais eficiente. Lê uma vez, escreve uma vez.
     */
    public void editar(T entity) throws IOException {
        String path = getEntityFileName();
        List<String> linhas = FileHandler.readAllLines(path);
        ArrayList<String> novasLinhas = new ArrayList<>();

        int entityId = getEntityId(entity);

        for (String linha : linhas) {
            T currEntity = stringToEntity(linha);

            if (entityId == getEntityId(currEntity)) {
                novasLinhas.add(entityToString(entity)); // Adiciona a entidade ATUALIZADA
            } else {
                novasLinhas.add(linha); // Adiciona a linha antiga inalterada
            }
        }

        // Sobrescreve o arquivo com a lista de linhas atualizada
        FileHandler.writeAllLines(path, novasLinhas);
    }

    /**
     * TEMPLATE METHOD: Exclui uma entidade.
     * Refatorado para ser muito mais eficiente. Lê uma vez, escreve uma vez.
     */
    public void excluir(T entity) throws IOException {
        String path = getEntityFileName();
        List<String> linhas = FileHandler.readAllLines(path);
        ArrayList<String> novasLinhas = new ArrayList<>();

        int entityId = getEntityId(entity);

        for (String linha : linhas) {
            T currEntity = stringToEntity(linha);

            if (entityId != getEntityId(currEntity)) {
                novasLinhas.add(linha); // Adiciona apenas se NÃO for a entidade para excluir
            }
        }

        // Sobrescreve o arquivo com a lista de linhas atualizada
        FileHandler.writeAllLines(path, novasLinhas);
    }
}