package classes.repository;

import classes.Motoboy;

// Implementação concreta para a entidade Motoboy
public class MotoboyRepository extends AbstractRepository<Motoboy> {

    @Override
    protected String getEntityFileName() {
        return "motoboys.txt";
    }

    @Override
    protected String entityToString(Motoboy entity) {
        return entity.toString(); // Usa o toString() já definido em Motoboy
    }

    @Override
    protected Motoboy stringToEntity(String line) {
        // Lógica copiada de ManipuladorArquivo.stringToMotoboy
        String valores[] = line.split(";");

        int codMotoboy = Integer.parseInt(valores[0]);
        float comissao = Float.parseFloat(valores[1]);
        boolean disponibilidade = Boolean.valueOf(valores[2]);
        String cpf = valores[3];
        String nome = valores[4];
        String email = valores[5];
        String senha = valores[6];
        String dataDeNascimento = valores[7];
        String telefone = valores[8];
        String inicioExpediente = valores[9];
        String finalExpediente = valores[10];

        return new Motoboy(codMotoboy, comissao, disponibilidade, cpf, nome, email, senha, dataDeNascimento, telefone, inicioExpediente, finalExpediente);
    }

    @Override
    protected int getEntityId(Motoboy entity) {
        return entity.getCodMotoboy(); // Retorna o ID específico do Motoboy
    }
}