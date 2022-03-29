package br.com.meli.helloword.repository;

import br.com.meli.helloword.entity.Seller;
import br.com.meli.helloword.util.ArquivoUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SellerRepository implements RepositoryWave5<Seller> {

    private static final String ANUNCIOS_TXT = "anuncios.txt";
    private ArquivoUtil<Seller> arquivoUtil;

    @Override
    public Seller insere(Seller seller) {
        System.out.println("realizando a insercao do seller no arquivo");
        return null;
        //arquivoUtil = new ArquivoUtil<Seller>(seller);
        //arquivoUtil.escreve(ANUNCIOS_TXT);
    }

    @Override
    public Seller obter(String codigo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Seller> lista() {
        // TODO Auto-generated method stub
        return null;
    }
}
