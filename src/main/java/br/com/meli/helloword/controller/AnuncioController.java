package br.com.meli.helloword.controller;

import br.com.meli.helloword.dto.AnuncioDTO;
import br.com.meli.helloword.entity.Anuncio;
import br.com.meli.helloword.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Controller
@RestController   //se a controller tiver apenas metodos que do tipo REST usamos essa anotação, dispensando o uso do @ResponseBody
@Api(value = "Anuncios")
public class AnuncioController {

    //http://localhost:8080/sellers/linguagem=GO  RequestParam
    //http://localhost:8080/sellers/GO            PathVariable

    @Autowired
    private AnuncioService anuncioService;

    public AnuncioController() {
        System.out.println("criando objeto da classe " + this.getClass().getName());
        //anuncioService = new AnuncioService();
    }

    //respondendo a uma chamada get http (endpoints)
    @ApiOperation(value = "Check if service is up")
    @GetMapping("/ping")
//    @ResponseBody
    public String ping() {
        return "pong";
    }

    @GetMapping("/marianas")
    public String marianas() {
        return "mariana 1 and mariana 2";
    }

    @GetMapping("/hiro")
    public List<String> pegaLivrosDoHiro(@RequestParam(name = "tipo", required = false) String tipo) {
        if (tipo != null) {
            if (tipo.equalsIgnoreCase("financeiro")) {
                return Arrays.asList("A Revolta de Atlas", "A revolução dos bichos", "Pai rico pai pobre", "A ética da liberdade");
            }
        }
        return Arrays.asList("Corte de espinhos", "A saga do tigre");
    }

    @ApiOperation(value = "Fetch all existing ads based on params")
    @GetMapping("/anuncios")
    public ResponseEntity<List<AnuncioDTO>> retorna(@RequestParam(required = false, name = "c") String categoria,
                                    @RequestParam(required = false, name = "p") Double preco) {
        List<AnuncioDTO> result = AnuncioDTO.converte(anuncioService.lista(categoria, preco));
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/anuncios/menor/{p}")
//    public List<Anuncio> anuncios(@PathVariable(name = "p") double preco) {
//        return anuncios.stream().filter(a -> a.getValor() <= preco).collect(Collectors.toList());
//    }

    @ApiOperation(value = "Create a new ad passed on body")
    @PostMapping("/anuncios")
    public ResponseEntity<AnuncioDTO> salvar(@Valid @RequestBody AnuncioDTO dto, UriComponentsBuilder uriBuilder) {
        Anuncio anuncio = dto.converte();
        anuncioService.salvar(anuncio, Arrays.asList(
                new AnuncioTituloValidator(anuncio),
                new AnuncioCategoriaValidator(anuncio),
                new AnuncioValorValidator(anuncio),
                new AnuncioDuplicidadeValidator(anuncio)
        ));
        //esta uri sinaliza ao cliente o caminho a ser usado para recuperar o recurso que esta sendo criado
        URI uri = uriBuilder
                .path("/usuarios/{id}")
                .buildAndExpand(anuncio.getCodigo())
                .toUri();
        AnuncioDTO d = dto.converte(anuncio);
        return ResponseEntity.created(uri).body(d);
    }

    @ApiOperation(value = "Fetch an especific ad based on code")
    @GetMapping("/anuncios/{codigo}")
    public ResponseEntity<AnuncioDTO> obter(@PathVariable String codigo) {
        Anuncio anuncio = anuncioService.obter(codigo);
        return ResponseEntity.ok(new AnuncioDTO().converte(anuncio));
//        Optional<Anuncio> op = anuncios.stream().filter(a -> a.getCodigo().equals(codigo)).findFirst();
//        if (op.isPresent()) {
//            return ResponseEntity.ok(new AnuncioDTO().converte(op.get()));
//        }
//        return ResponseEntity.noContent().build();
    }
}
