package br.com.meli.helloword.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {

    private String tipo;
    private String mensagem;
}
