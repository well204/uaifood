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
