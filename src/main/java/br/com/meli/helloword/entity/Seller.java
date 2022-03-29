package br.com.meli.helloword.entity;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seller {

    private String codigo;
    private String nome;
    private LocalDate dataNascimento;
    private String uf;

    @Override
    public String toString() {
        return codigo.concat(";").concat(nome).concat(";").concat(dataNascimento.toString());
    }
}
