package br.com.meli.helloword.service;

import br.com.meli.helloword.entity.Anuncio;
import br.com.meli.helloword.repository.AnuncioRepository;
import br.com.meli.helloword.repository.RepositoryWave5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnuncioService {

    //    private static List<Anuncio> anuncios = Collections.synchronizedList(new ArrayList<>());

    //    static {
//        anuncios.addAll(Arrays.asList(
//                new Anuncio(1, "MLB123","Capsulas de café", "bebidas", 400),
//                new Anuncio(1, "MLB100","fone JBL", "eletronico", 500),
//                new Anuncio(1, "MLB124","mouse bluetooth", "eletronico",600),
//                new Anuncio(1, "MLB165","copo stanley", "outros", 700),
//                new Anuncio(1, "MLB114","jogo de chaves", "jogos", 800),
//                new Anuncio(1, "MLB158","ovo de pascoa", "comida",900)
//        ));
//    }

//    @Autowired
    private RepositoryWave5<Anuncio> repository;

    public AnuncioService(AnuncioRepository repository) {
        this.repository = repository;
    }

    public AnuncioService() {
        System.out.println("criando objeto da classe " + this.getClass().getName());
    }

    public synchronized List<Anuncio> lista(String categoria, Double preco) {
        if (categoria != null && !categoria.isEmpty() && preco != null) {
            return repository.lista().stream()
                    .filter(a -> a.getCategoria().equalsIgnoreCase(categoria))
                    .filter(a -> a.getValor() <= preco)
                    .collect(Collectors.toList());
        }
        if (categoria != null && categoria.isEmpty()) {
            return repository.lista().stream()
                    .filter(a -> a.getCategoria().equalsIgnoreCase(categoria))
                    .collect(Collectors.toList());
        }
        if (preco != null) {
            return repository.lista().stream()
                    .filter(a -> a.getValor() <= preco)
                    .collect(Collectors.toList());
        }
        return repository.lista();
    }

    public synchronized Anuncio obter(String codigo) {
        Optional<Anuncio> optional = repository.lista().stream().filter(a -> a.getCodigo().equals(codigo)).findFirst();
        return optional.orElse(new Anuncio());
//        if ((optional.isPresent()) {   //tudo isso substituido pela linha 52
//            return optional.get();
//        } else {
//            return new Anuncio();
//        }
    }

    private boolean anuncioExistente(Anuncio anuncio) {
        return repository.lista().stream().anyMatch(a -> a.getTitulo().equals(anuncio.getTitulo()) && a.getValor() == anuncio.getValor());
    }

    public synchronized void salvar(Anuncio anuncio, List<Validador> validadores) {
        validadores.forEach(v -> {
            try {
                v.valida();
                if(!anuncioExistente(anuncio)) {
                    repository.insere(anuncio);
                }else {
                    throw new RuntimeException("Anuncio já cadastrado");
                }
            } catch (ValidacaoException e) {
                e.printStackTrace();
            }
        });
    }

    public synchronized void salvar(Anuncio anuncio) {
        if(!anuncioExistente(anuncio)) {
            repository.insere(anuncio);
        }else {
            throw new RuntimeException("Anuncio já cadastrado");
        }
    }

//    public void salvar(Anuncio anuncio, List<Validador> validadores) {
//        validadores.forEach(v -> {
//            try {
//                v.valida();
//                repository.insere(anuncio);
//            } catch (ValidacaoException e) {
//                e.printStackTrace();
//            }
//        });
//        if (anuncioExistente(anuncio)) {
//            throw new RuntimeException("anuncio já cadastrado");
//        }
//        repository.insere(anuncio);
//    }
}
