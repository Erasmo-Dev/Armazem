package br.com.estoque.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuantidadeSuperaCapacidadeException extends RuntimeException {

    public QuantidadeSuperaCapacidadeException(float quantidadeMax) {
        super("Quantidade nao pode ser superior a " + quantidadeMax);
    }
}
