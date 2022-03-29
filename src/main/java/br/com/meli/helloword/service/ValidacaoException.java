package br.com.meli.helloword.service;

public class ValidacaoException extends Exception {

    private static final long serialVersionUID = 8514406528027778757L;

    public ValidacaoException(String mensagem) {
        super(mensagem);
    }
}
