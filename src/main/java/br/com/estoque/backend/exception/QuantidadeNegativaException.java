package br.com.estoque.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuantidadeNegativaException extends RuntimeException {

    public QuantidadeNegativaException() {
        super("Quantidade nao pode ser menor que zero");
    }
}
