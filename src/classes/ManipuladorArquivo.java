package classes;

import classes.repository.ClienteRepository;
import classes.repository.MotoboyRepository;
import classes.repository.ProdutoRepository;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Classe refatorada para atuar como um "Facade" simples.
 * Ela mantém os métodos estáticos (para não quebrar o resto do código),
 * mas delega toda a lógica de persistência para as classes de Repositório
 * apropriadas, que agora usam o padrão Template Method.
 */
public class ManipuladorArquivo {

    // Instâncias estáticas dos repositórios
    private static final ClienteRepository clienteRepo = new ClienteRepository();
    private static final MotoboyRepository motoboyRepo = new MotoboyRepository();
    private static final ProdutoRepository produtoRepo = new ProdutoRepository();

    //
    // Métodos de I/O (leitor, escritor, limpar) foram movidos para AbstractRepository
    // Métodos de conversão (stringTo...) foram movidos para os Repositórios concretos
    //

    // --- CRUD Cliente ---

    public static void armazenar(Cliente cliente) throws IOException {
        clienteRepo.armazenar(cliente);
    }

    public static ArrayList<Cliente> carregarClientes() throws IOException {
        return clienteRepo.carregarTodos();
    }

    public static void editar(Cliente cliente) throws IOException {
        clienteRepo.editar(cliente);
    }

    public static void excluir(Cliente cliente) throws IOException {
        clienteRepo.excluir(cliente);
    }

    // --- CRUD Motoboy ---

    public static void armazenar(Motoboy motoboy) throws IOException {
        motoboyRepo.armazenar(motoboy);
    }

    public static ArrayList<Motoboy> carregarMotoboys() throws IOException {
        return motoboyRepo.carregarTodos();
    }

    public static void editar(Motoboy motoboy) throws IOException {
        motoboyRepo.editar(motoboy);
    }

    public static void excluir(Motoboy motoboy) throws IOException {
        motoboyRepo.excluir(motoboy);
    }

    // --- CRUD Produto ---

    public static void armazenar(Produto produto) throws IOException {
        produtoRepo.armazenar(produto);
    }

    public static ArrayList<Produto> carregarProdutos() throws IOException {
        return produtoRepo.carregarTodos();
    }

    public static void editar(Produto produto) throws IOException {
        produtoRepo.editar(produto);
    }

    public static void excluir(Produto produto) throws IOException {
        produtoRepo.excluir(produto);
    }
}