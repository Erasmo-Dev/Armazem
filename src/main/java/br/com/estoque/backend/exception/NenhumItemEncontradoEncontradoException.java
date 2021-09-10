package br.com.estoque.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NenhumItemEncontradoEncontradoException extends RuntimeException{

    public NenhumItemEncontradoEncontradoException() {
        super("Nenhum item encontrado");
    }
}
