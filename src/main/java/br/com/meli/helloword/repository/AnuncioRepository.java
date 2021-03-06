package br.com.meli.helloword.repository;

import br.com.meli.helloword.entity.Anuncio;
import br.com.meli.helloword.util.ArquivoUtil;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AnuncioRepository implements RepositoryWave5<Anuncio> {

    private static final String ANUNCIOS_TXT = "anuncios.txt";
    private ArquivoUtil<Anuncio> arquivoUtil;

    @Override
    public Anuncio insere(Anuncio anuncio) {
        arquivoUtil = new ArquivoUtil<Anuncio>(anuncio);
        arquivoUtil.escreve(ANUNCIOS_TXT);
        return anuncio;
    }
    //SOLID

    @Override
    public Anuncio obter(String codigo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Anuncio> lista() {
        arquivoUtil = new ArquivoUtil<Anuncio>();
        List<String> registros = arquivoUtil.leitura(ANUNCIOS_TXT);
        List<Anuncio> result = new ArrayList<>();
        registros.forEach(r->{
            String[] campos = r.split(";");
            Anuncio anuncio = new Anuncio(Integer.valueOf(campos[0]), campos[1], campos[2], campos[3], Double.valueOf(campos[4]));
            result.add(anuncio);
        });
        return result;
    }
}
