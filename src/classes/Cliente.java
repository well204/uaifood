package classes;

// subclasse da superclasse usuario.


// dados especificos: codigo do cliente e endereço.

public class Cliente extends Usuario {
    private int codCliente;
    private String endereco;

    public Cliente(int codCliente, String endereco, String cpf, String nome, String email, String senha, String dataDeNascimento, String telefone) {
        super(cpf, nome, email, senha, dataDeNascimento, telefone);
        this.codCliente = codCliente;
        this.endereco = endereco;
    }


    @Override
    public String toString() {
        return String.valueOf(this.codCliente) + ";"
                + this.endereco + ";"
                + this.cpf + ";"
                + this.nome + ";"
                + this.email + ";"
                + this.senha + ";"
                + this.dataDeNascimento + ";"
                + this.telefone;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
