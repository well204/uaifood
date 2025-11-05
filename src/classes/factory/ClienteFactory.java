package classes.factory;

import classes.Cliente;
import classes.ManipuladorArquivo;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Padrão Factory: Centraliza a lógica de criação de Clientes.
 * Agora, esta classe é a única responsável por gerar o ID e
 * salvar o novo cliente no banco de dados.
 */
public class ClienteFactory {

    /**
     * Cria, gera o ID, salva e retorna um novo objeto Cliente.
     */
    public static Cliente createCliente(String endereco, String cpf, String nome, String email, String senha, String dataDeNascimento, String telefone) throws IOException {

        // 1. Lógica de geração de ID (movida do Cliente.init())
        int curr_id = 0;
        try {
            ArrayList<Cliente> clientes = ManipuladorArquivo.carregarClientes();
            if (clientes.size() > 0) {
                curr_id = clientes.get(clientes.size()-1).getCodCliente() + 1;
            }
        } catch(IOException ex) {
            curr_id = 0; // O arquivo não existe ou está vazio
        }

        // 2. Cria o objeto com o novo ID
        Cliente novoCliente = new Cliente(curr_id, endereco, cpf, nome, email, senha, dataDeNascimento, telefone);

        // 3. Persiste o objeto (lógica movida do Cliente.init())
        ManipuladorArquivo.armazenar(novoCliente);

        // 4. Retorna o objeto criado
        return novoCliente;
    }
}