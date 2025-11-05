package classes.repository;

import classes.Cliente;

// Implementação concreta para a entidade Cliente
public class ClienteRepository extends AbstractRepository<Cliente> {

    @Override
    protected String getEntityFileName() {
        return "clientes.txt";
    }

    @Override
    protected String entityToString(Cliente entity) {
        return entity.toString(); // Usa o toString() já definido em Cliente
    }

    @Override
    protected Cliente stringToEntity(String line) {
        // Lógica copiada de ManipuladorArquivo.stringToCliente
        String valores[] = line.split(";");

        int codCliente = Integer.parseInt(valores[0]);
        String endereco = valores[1];
        String cpf = valores[2];
        String nome = valores[3];
        String email = valores[4];
        String senha = valores[5];
        String dataDeNascimento = valores[6];
        String telefone = valores[7];

        return new Cliente(codCliente, endereco, cpf, nome, email, senha, dataDeNascimento, telefone);
    }

    @Override
    protected int getEntityId(Cliente entity) {
        return entity.getCodCliente(); // Retorna o ID específico do Cliente
    }
}