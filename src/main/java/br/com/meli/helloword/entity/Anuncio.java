package br.com.meli.helloword.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Anuncio {

    private Integer id;
    private String codigo;
    private String titulo;
    private String categoria;
    private double valor;

    @Override
    public String toString() {
        return String.valueOf(id).concat(";").concat(codigo).concat(";")
                .concat(titulo).concat(";").concat(categoria).concat(";").concat(String.valueOf(valor));
    }
}
