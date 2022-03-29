package br.com.meli.helloword.service;

import br.com.meli.helloword.entity.Anuncio;

public class AnuncioDuplicidadeValidator implements Validador {

    private Anuncio anuncio;

    public AnuncioDuplicidadeValidator(Anuncio anuncio) {
        this.anuncio = anuncio;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void valida() throws ValidacaoException {
        // TODO Auto-generated method stub
    }
}
