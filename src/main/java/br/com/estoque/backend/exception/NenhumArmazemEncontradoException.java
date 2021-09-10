package br.com.estoque.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NenhumArmazemEncontradoException extends RuntimeException{

    public NenhumArmazemEncontradoException() {
        super("Nenhum armazem encontrado");
    }
}
