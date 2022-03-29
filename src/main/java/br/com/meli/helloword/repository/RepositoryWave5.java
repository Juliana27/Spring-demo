package br.com.meli.helloword.repository;

import java.util.List;

public interface RepositoryWave5 <T> {

    T insere(T t);
    T obter(String codigo);
    List<T> lista();
}
