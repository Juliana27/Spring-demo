package br.com.meli.helloword.service;

import br.com.meli.helloword.entity.Anuncio;

public class AnuncioTituloValidator implements Validador{

    private Anuncio anuncio;

    public AnuncioTituloValidator(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    @Override
    public void valida() throws ValidacaoException {
        if(anuncio.getTitulo().length() < 5)
            throw new ValidacaoException("titulo precisa ter tamanho maior que 4");
    }
}
