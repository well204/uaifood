package classes.factory;

import classes.Motoboy;
import classes.ManipuladorArquivo;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Padrão Factory: Centraliza a lógica de criação de Motoboys.
 */
public class MotoboyFactory {

    /**
     * Cria, gera o ID, salva e retorna um novo objeto Motoboy.
     */
    public static Motoboy createMotoboy(float comissao, boolean disponibilidade,
                                        String cpf, String nome, String email, String senha,
                                        String dataDeNascimento, String telefone,
                                        String inicioExpediente, String finalExpediente) throws IOException {

        // 1. Lógica de geração de ID (movida do Motoboy.init())
        int curr_id = 0;
        try {
            ArrayList<Motoboy> motoboys = ManipuladorArquivo.carregarMotoboys();
            if (motoboys.size() > 0) {
                curr_id = motoboys.get(motoboys.size()-1).getCodMotoboy() + 1;
            }
        } catch(IOException ex) {
            curr_id = 0; // O arquivo não existe ou está vazio
        }

        // 2. Cria o objeto com o novo ID
        Motoboy novoMotoboy = new Motoboy(curr_id, comissao, disponibilidade, cpf, nome, email, senha, dataDeNascimento, telefone, inicioExpediente, finalExpediente);

        // 3. Persiste o objeto (lógica movida do Motoboy.init())
        ManipuladorArquivo.armazenar(novoMotoboy);

        // 4. Retorna o objeto criado
        return novoMotoboy;
    }
}