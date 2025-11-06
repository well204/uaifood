package classes.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//  Padrão Facade: Esta classe atua como uma fachada para simplificar

public class FileHandler {

    /**
     * Lê todas as linhas de um arquivo e as retorna como uma Lista de Strings.
     * @param path O caminho para o arquivo.
     * @return Uma Lista de Strings, onde cada string é uma linha do arquivo.
     * @throws IOException Se ocorrer um erro de leitura (ex: arquivo não encontrado).
     */
    public static List<String> readAllLines(String path) throws IOException {
        ArrayList<String> linhas = new ArrayList<>();
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = buffRead.readLine()) != null) {
                if (linha.length() > 0) {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            // Se o arquivo não existir (primeira execução), apenas retorna uma lista vazia.
            return new ArrayList<>();
        }
        return linhas;
    }

    /**
     * Escreve uma lista de Strings em um arquivo, SOBRESCREVENDO qualquer conteúdo existente.
     * @param path O caminho para o arquivo.
     * @param lines A Lista de Strings a ser escrita.
     * @throws IOException Se ocorrer um erro de escrita.
     */
    public static void writeAllLines(String path, List<String> lines) throws IOException {
        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, false))) { // false = modo de sobrescrita
            for (String linha : lines) {
                buffWrite.append(linha + "\n");
            }
        }
    }

    /**
     * Adiciona uma nova linha ao FINAL de um arquivo (append).
     * @param path O caminho para o arquivo.
     * @param line A string a ser adicionada.
     * @throws IOException Se ocorrer um erro de escrita.
     */
    public static void appendLine(String path, String line) throws IOException {
        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path, true))) { // true = modo de "append"
            buffWrite.append(line + "\n");
        }
    }
}