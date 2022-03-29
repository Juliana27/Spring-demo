package br.com.meli.helloword.controller;

import br.com.meli.helloword.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SellerController {

    @Autowired
    private AnuncioService anuncioService;

    public SellerController() {
        System.out.println("criando objeto da classe " + this.getClass().getName());
//        anuncioService = new AnuncioService();
    }

    @GetMapping("/sellers")
    public ResponseEntity<List<String>> obterSellers() {
        return ResponseEntity.ok(Arrays.asList("Juliana", "Vitor", "Thiago"));
    }
}
