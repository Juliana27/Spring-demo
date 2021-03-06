package br.com.meli.helloword.service;

import br.com.meli.helloword.entity.Seller;
import br.com.meli.helloword.repository.RepositoryWave5;

import java.time.LocalDate;
import java.time.Period;

public class SellerService {

    private RepositoryWave5<Seller> sellerRepository;
    //private ReclamacoesService reclamacoesService;
    //private Devolucoes devolucoesService;

    public SellerService(RepositoryWave5<Seller> sellerRepository) {
        super();
        this.sellerRepository = sellerRepository;
    }

    public Seller salva(Seller seller) {
        if (maiorIdade(seller)) {
            sellerRepository.insere(seller);
            return seller;
        } else {
            throw new RuntimeException("um vendedor deve ser maior de idade");
        }
    }

    private boolean maiorIdade(Seller seller) {
        LocalDate birthDate = seller.getDataNascimento();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears()>=18;
    }

    public boolean remove(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            throw new RuntimeException("informe o codigo");
        }
        return true;
    }
}
